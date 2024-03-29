package org.helei.retinalsegmentation.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.helei.retinalsegmentation.common.threadlocal.UserHolder;
import org.helei.retinalsegmentation.converter.UserConverter;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.dto.UserAlterForm;
import org.helei.retinalsegmentation.dto.UserDTO;
import org.helei.retinalsegmentation.dto.UserInfo;
import org.helei.retinalsegmentation.entity.User;
import org.helei.retinalsegmentation.entity.UserUploadRecord;
import org.helei.retinalsegmentation.mapper.UserMapper;
import org.helei.retinalsegmentation.query.UploadRecordQuery;
import org.helei.retinalsegmentation.service.IUserService;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.helei.retinalsegmentation.service.IUserUserUploadRecordService;
import org.helei.retinalsegmentation.service.MailService;
import org.helei.retinalsegmentation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-12-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IUserUploadRecordService userUploadRecordService;



    @Override
    public Result registerUser(User user) {
        String password = user.getPassword();
        String username = user.getUsername();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.fail("用户名或密码不能为空或含空格");
        }
        if(password.length() < 6 || password.length() > 16){
            return Result.fail("密码长度需在6到16位");
        }

        Integer count = query().eq("username", username).count();
        if(count != 0) {
            return Result.fail("该用户名已被使用");
        }

        if(RegexUtils.isEmailInvalid(user.getEmail())) {
            return Result.fail("邮箱格式错误");
        }

        //TODO 判断邮箱是否被使用，或者正在激活


        user.setIsValid(false);
        user.setPassword(PasswordEncoder.encode(password));
        save(user);

        User one = query().eq("username", username).select("id").one();
        // TODO 判断是否存在one，不存在抛出异常，事物回滚

        // TODO 放入消息队列，异步发邮件
        sendConfirmMail(one.getId(), user.getEmail());

        return Result.ok(UserConverter.INSTANCE.entity2dto(user));
    }

    /**
     * 发送确认邮件
     * @param id
     * @param email
     */
    private void sendConfirmMail(Long id, String email){
        Context context = new Context();
        context.setVariable("id", id);
        String emailContent = templateEngine.process("userRegistConfirm", context);
        mailService.sendHtmlMail(email, "注册确认", emailContent);

    }


    @Override
    public Result registerConfirm(Long id) {
        User one = query().eq("id", id).select("is_valid").one();
        if(one == null) {
            return Result.fail("确认失败，不存在该用户");
        }
        if(one.getIsValid()) {
            return Result.fail("你已经确认过了");
        }
        boolean res = update().eq("id", id).set("is_valid", 1).update();
        if(!res) {
            return Result.fail("确认失败");
        }
        return Result.ok();
    }


    @Override
    public void getCaptchaImg(String username, HttpServletResponse response) {
        User user = query().eq("username", username).one();
        if(user == null){
            return ;
        }
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            ValidateCodeUtil.getRandCodeImageAndSaveRedis(RedisConstants.USER_LOGIN_CHECKCODE_KEY,
                    username, response, stringRedisTemplate, RedisConstants.USER_LOGIN_CHECKCODE_TTL, TimeUnit.MINUTES);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result login(String username, String password, String checkCode) {
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.fail("用户名或密码不能为空");
        }

        if(StrUtil.isBlank(checkCode) || checkCode.length() > 4){
            return Result.fail("验证码格式错误");
        }
        String rCode = stringRedisTemplate.opsForValue().get(RedisConstants.USER_LOGIN_CHECKCODE_KEY+username);
        if(StrUtil.isBlank(rCode)) {
            return Result.fail("验证码已过期");
        }
        if(!checkCode.equalsIgnoreCase(rCode)) {
            return Result.fail("验证码错误");
        }

        User dbUser = query().eq("username", username).one();
        if(dbUser == null) {
            return Result.fail("不存在该用户");
        }

        if(!PasswordEncoder.matches(dbUser.getPassword(), password)) {
            return Result.fail("密码错误");
        }

        if(!dbUser.getIsValid()) {
            return Result.fail("用户尚未验证邮箱，请前往邮箱确认");
        }

        /*
        //生成jwt令牌返回
        Map<String, String> map = new HashMap<>();
        map.put("strId", UserIDBase64.encoderUserID(dbUser.getId()));
        map.put("username", username);
        String token = JwtUtil.createJWT(JSON.toJSONString(map), 216000l*240);
        */

        //token, redis管理
        String token = UUID.randomUUID().toString(true);
        String tokenKey = RedisConstants.USER_LOGIN_TOKEN_KEY + token;
        UserDTO userDTO = UserConverter.INSTANCE.entity2dto(dbUser);

        stringRedisTemplate.opsForValue().set(tokenKey, JSONUtil.toJsonStr(userDTO),
                RedisConstants.USER_LOGIN_TOKEN_TTL, TimeUnit.DAYS);

        JSONObject object = JSONUtil.createObj().set("token", token)
                .set("loginUser", UserConverter.INSTANCE.entity2dto(dbUser));
        return Result.ok(object);
    }

    @Override
    public Result noPasswordValid() {
        return Result.ok(UserHolder.getUser());
    }

    @Override
    public Result alterPassword(UserAlterForm form) {
        String confirmPwd = form.getConfirmPwd();
        String newPwd = form.getNewPwd();
        String oldPwd = form.getOldPwd();
        if(StrUtil.isBlank(oldPwd) ||
                StrUtil.isBlank(newPwd) ||
                StrUtil.isBlank(confirmPwd)) {
            return Result.fail("数据不能含空");
        }
        if(!confirmPwd.equals(newPwd)) {
            return Result.fail("两次输入密码不一致");
        }
        if(confirmPwd.equals(oldPwd)) {
            return Result.fail("修改前后密码不能相同");
        }
        Long uid = form.getUid();
        if(uid == null) {
            return Result.fail("缺少用户id");
        }

        User dbUser = query().eq("id", uid).one();
        if(dbUser == null) {
            return Result.fail("不存在该用户");
        }

        if(!PasswordEncoder.matches(dbUser.getPassword(), oldPwd)) {
            return Result.fail("原密码错误");
        }

        boolean update = update()
                .set("password", PasswordEncoder.encode(newPwd))
                .eq("id", uid)
                .update();
        if(!update) return Result.fail("修改失败");
        return Result.ok();
    }

    @Override
    public Result getUserInfo() {
        Long uid = UserHolder.getUser().getId();
        User user = query().eq("id", uid).one();
        UserInfo info = userUploadRecordService.queryCount(uid);
        info.setUsername(user.getUsername());
        info.setCreateTime(user.getCreateTime());
        info.setEmail(user.getEmail());
        return Result.ok(info);
    }

    @Override
    public Result uploadSrcImage(MultipartFile file) {
        if(file == null) {
            return Result.fail("请选择图片");
        }
        String contentType = file.getContentType();
        if(!("image/png".equals(contentType) || "image/jpeg".equals(contentType))) {
            return Result.fail("图片格式错误，需为png或jpg");
        }

        //TODO 可为登陆用户添加新的功能


        UserUploadRecord uploadRecord = null;
        try {
            String username = UserHolder.getUser().getUsername();
            String saveFile = FileUtil
                    .saveFile(
                            file,
                            FileUtil.getUserUploadSrcImagePath(username),
                            FileUtil.getSaveFileName(file.getOriginalFilename()));
            System.out.println(saveFile);
            //记录
            uploadRecord = userUploadRecordService
                    .recordUserUpload(UserHolder.getUser().getId(), FileUtil.getSourcePath(saveFile));
        } catch (IOException e) {
            e.printStackTrace();
            Result.fail("保存文件出错");
        }
        return Result.ok(uploadRecord);
    }

}

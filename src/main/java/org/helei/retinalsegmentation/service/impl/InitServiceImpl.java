package org.helei.retinalsegmentation.service.impl;

import cn.hutool.core.util.StrUtil;
import org.helei.retinalsegmentation.common.threadlocal.IdNumberHolder;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.service.InitService;
import org.helei.retinalsegmentation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class InitServiceImpl implements InitService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisIdWorker redisIdWorker;

    @Override
    public void getCaptchaImg(HttpServletResponse response) {
        String idNumber = IdNumberHolder.getIdNumber();
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            ValidateCodeUtil.getRandCodeImageAndSaveRedis(RedisConstants.INIT_CHECKCODE_KEY,
                    idNumber, response, stringRedisTemplate, RedisConstants.INIT_CHECKCODE_TTL, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result getIdNumber() {
        long idNumber = redisIdWorker.nextId(SystemConstants.REDIS_ID_WORKER_KEY_INIT);
        stringRedisTemplate.opsForValue().set(RedisConstants.INIT_USER_IDNUMBER_KEY+idNumber,
                String.valueOf(idNumber), RedisConstants.INIT_USER_IDNUMBER_TTL, TimeUnit.MINUTES);
        return Result.ok(String.valueOf(idNumber));
    }

    @Override
    public Result verifyValidCode(String validCode) {
        if(StrUtil.isBlank(validCode)) {
            return Result.fail("?????????????????????");
        }

        String idNumber = IdNumberHolder.getIdNumber();

        String key = RedisConstants.INIT_CHECKCODE_KEY + idNumber;
        String redisVC = stringRedisTemplate.opsForValue().get(key);

        if(StrUtil.isBlank(redisVC)) {
            return Result.fail("?????????????????????????????????");
        }

        if(!redisVC.equals(validCode)) {
            return Result.fail("???????????????");
        }
        return Result.ok();
    }

    @Override
    public Result uploadSrcImage(MultipartFile file) {
        if(file == null) {
            Result.fail("???????????????");
        }
        String contentType = file.getContentType();
        if(!"image/png".equals(contentType) || !"image/jpg".equals(contentType)) {
            Result.fail("???????????????????????????png???jpg");
        }

        String idNumber = IdNumberHolder.getIdNumber();
        String saveFile = "";
        try {
            String imagePath = FileUtil.getTempUploadSrcImagePath(idNumber);

            File dir = new File(imagePath);
            if(dir.exists() && dir.isDirectory()){
                String[] list = dir.list();
                if(list != null && list.length != 0){
                    return Result.fail("????????????????????????????????????");
                }
            }

            saveFile = FileUtil.saveFile(file, imagePath);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("??????????????????");
        }
        return Result.ok(saveFile);
    }

    @Override
    public Result getSrcImageList() {
        //TODO ?????????????????????????????????url
        String idNumber = IdNumberHolder.getIdNumber();
        File dir = new File( FileUtil.getTempUploadSrcImagePath(idNumber));
        List<String> paths = null;
        try{
            if(dir.exists() && dir.isDirectory()){
                String[] list = dir.list();
                if(list != null && list.length != 0){
                   paths = Arrays.stream(list).map(s->"/images/temp/srcImages/"+idNumber+"/"+s)
                           .collect(Collectors.toList());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.ok(paths);
    }
}

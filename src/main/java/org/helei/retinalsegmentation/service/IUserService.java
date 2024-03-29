package org.helei.retinalsegmentation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.dto.UserAlterForm;
import org.helei.retinalsegmentation.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-12-08
 */
public interface IUserService extends IService<User>, FileService{

    /**
     * 注册用户
     * @param user
     * @return
     */
    Result registerUser(User user);

    /**
     * 注册用户确认
     * @param id
     * @return
     */
    Result registerConfirm(Long id);

    /**
     * 用户登陆验证码
     * @param response
     */
    void getCaptchaImg(String username, HttpServletResponse response);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @param code
     * @return
     */
    Result login(String username, String password, String code);

    /**
     * 无密码验证登陆状态，根据header中的token
     * @return
     */
    Result noPasswordValid();

    /**
     * 修改密码
     * @param form
     * @return
     */
    Result alterPassword(UserAlterForm form);

    /**
     * 获取用户个人信息页面的各种数据
     * @return
     */
    Result getUserInfo();
}

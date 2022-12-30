package org.helei.retinalsegmentation.interceptor.user;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.helei.retinalsegmentation.common.threadlocal.UserHolder;
import org.helei.retinalsegmentation.dto.UserDTO;
import org.helei.retinalsegmentation.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class RefreshTokenInterceptor implements HandlerInterceptor {

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头的token
        String token = request.getHeader("userToken");

        //redis获取用户
        String json = stringRedisTemplate.opsForValue().get(RedisConstants.USER_LOGIN_TOKEN_KEY + token);
        if(StrUtil.isNotBlank(json)){
            UserDTO userDTO = JSONUtil.toBean(json, UserDTO.class);

            //更新redis
            stringRedisTemplate.expire(RedisConstants.USER_LOGIN_TOKEN_KEY + token, RedisConstants.USER_LOGIN_TOKEN_TTL, TimeUnit.DAYS);

            //保存到ThreadLocal
            UserHolder.saveUser(userDTO);
        }

        //通过
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}

package org.helei.retinalsegmentation.interceptor.temp;


import cn.hutool.core.util.StrUtil;
import org.helei.retinalsegmentation.utils.RedisConstants;
import org.helei.retinalsegmentation.common.threadlocal.IdNumberHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class RefreshIdNumberInterceptor implements HandlerInterceptor {

    public RefreshIdNumberInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头的idnumber
        String idNumber = request.getHeader("IdNumber");
//        System.out.println(idNumber);

        //redis获取id
        String key = RedisConstants.INIT_USER_IDNUMBER_KEY + idNumber;

        String redisIdNumber = stringRedisTemplate.opsForValue().get(key);

        if(StrUtil.isNotBlank(redisIdNumber)){
            //更新redis
            stringRedisTemplate.expire(key, RedisConstants.INIT_USER_IDNUMBER_TTL, TimeUnit.MINUTES);
            //保存到ThreadLocal
            IdNumberHolder.saveIdNumber(redisIdNumber);
        }
        //通过
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        IdNumberHolder.removeIdNumber();
    }
}

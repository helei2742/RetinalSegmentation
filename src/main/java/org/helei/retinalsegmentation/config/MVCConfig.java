package org.helei.retinalsegmentation.config;

import org.helei.retinalsegmentation.interceptor.temp.PrivilegeInterceptor;
import org.helei.retinalsegmentation.interceptor.temp.RefreshIdNumberInterceptor;
import org.helei.retinalsegmentation.interceptor.user.LoginInterceptor;
import org.helei.retinalsegmentation.interceptor.user.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.io.File;


@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //刷新拦截器，将idnumber存入threadlocal，并刷新redis中的时间
        registry.addInterceptor(new RefreshIdNumberInterceptor(stringRedisTemplate))
                .addPathPatterns("/init/**")
                .excludePathPatterns("/init/idNumber")
                .order(0);
        //临时用户权限校验拦截器
        registry.addInterceptor(new PrivilegeInterceptor())
                .addPathPatterns("/init/**")
                .excludePathPatterns("/init/idNumber")
                .order(1);


        //刷新拦截器
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/user/**")
                .excludePathPatterns(
                        "/user/validCode",
                        "/user/login")
                .order(2);
        //登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns(
                        "/user/validCode",
                        "/user/login")
                .order(3);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String dir = System.getProperty("user.dir");

        //System.out.println("项目当前路径："+dir);
        //构建路径
        File file=new File(dir+File.separatorChar+"images");
        if(!file.exists()){
            file.mkdir();
        }
        String resourceLocation=file.getAbsolutePath()+File.separatorChar;
        System.out.println(resourceLocation+">>>>>>");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+resourceLocation);
    }
}

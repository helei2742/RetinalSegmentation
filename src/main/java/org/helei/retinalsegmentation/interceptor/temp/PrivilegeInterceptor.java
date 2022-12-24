package org.helei.retinalsegmentation.interceptor.temp;

import cn.hutool.core.util.StrUtil;
import org.helei.retinalsegmentation.common.threadlocal.IdNumberHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivilegeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String idNumber = IdNumberHolder.getIdNumber();

        if(StrUtil.isBlank(idNumber)){
            response.setStatus(401);
            return false;
        }

        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        IdNumberHolder.removeIdNumber();
    }
}

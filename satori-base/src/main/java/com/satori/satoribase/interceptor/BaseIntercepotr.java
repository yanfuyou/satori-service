package com.satori.satoribase.interceptor;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

/**
 * @auth YanFuYou
 * @date 03/09/23 下午 06:36
 */
public class BaseIntercepotr implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (ObjectUtil.isNull(request.getHeader("requestId"))){
            request.setAttribute("requestId", IdUtil.simpleUUID());
        }
        if (ObjectUtil.isNull(request.getHeader("requestTime"))){
            request.setAttribute("requestTime", LocalDateTime.now());
        }
        if (ObjectUtil.isNull(request.getHeader("requestPath"))){
            request.setAttribute("requestPath", request.getServletPath());
        }

        response.addHeader("requestId",(String) request.getAttribute("requestId"));
        response.addHeader("requestTime",request.getAttribute("requestTime").toString());
        response.addHeader("requestPath",(String) request.getAttribute("requestPath"));
        return true;
    }
}

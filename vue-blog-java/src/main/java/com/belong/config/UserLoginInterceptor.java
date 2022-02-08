package com.belong.config;

import com.belong.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：验证用户是否登录
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置响应数据格式，防止浏览器接收中文出现乱码
        response.setContentType("text/html;charset=utf-8");

        // 1. 从请求头中获取token
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new RuntimeException("你还未登录，请先去登录");
        }

        // 2. 校验token
        Boolean verifyToken = JwtUtils.verifyToken(token);
        if (!verifyToken) {
            return  false;
        }

        return true;
    }
}

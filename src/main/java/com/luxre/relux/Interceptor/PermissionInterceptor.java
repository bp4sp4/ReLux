package com.luxre.relux.Interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String uri = request.getRequestURI();

        // 로그인하지 않은 사용자가 글쓰기 페이지에 접근할 때
        if (userId == null) {
            if (uri.startsWith("/post") || uri.startsWith("/post/create")) {
                response.sendRedirect("/user/login-view");
                return false;
            }
        } else {
            // 이미 로그인한 사용자가 로그인 또는 회원가입 페이지에 가려고 할 때 리다이렉트
            if (uri.equals("/user/login-view") || uri.equals("/user/join-view")) {
                response.sendRedirect("/post/list-view");
                return false;
            }
        }

        return true;
    }
}

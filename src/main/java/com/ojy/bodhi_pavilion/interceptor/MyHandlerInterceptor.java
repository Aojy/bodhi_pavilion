package com.ojy.bodhi_pavilion.interceptor;

import com.mysql.cj.Session;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute("emp") != null || session.getAttribute("user") != null) {
            return true;
        }
        String uri = request.getRequestURI();
        if (uri.contains("login") || uri.contains("error")) {
            return true;
        }
        return false;
    }
}

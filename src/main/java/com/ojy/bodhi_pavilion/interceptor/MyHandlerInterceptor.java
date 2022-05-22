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
        String flag = (String)session.getAttribute("loginFlag");
        if (flag != null) {
            if (session.getAttribute("emp") != null || session.getAttribute("user") != null) {
                return true;
            } else if (flag.equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/user");
            }
        }
        String uri = request.getRequestURI();
        if (uri.contains("login") || uri.contains("error")) {
            return true;
        }
        return false;
    }
}

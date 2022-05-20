package com.ojy.bodhi_pavilion.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Interceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new MyHandlerInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/admin",
                "/user",
                "/**/*.html",
                "/**/*.js",
                "/**/*.ico",
                "/**/*.woff",
                "/**/*.ttf",
                "/**/*.css"
        );
    }
}

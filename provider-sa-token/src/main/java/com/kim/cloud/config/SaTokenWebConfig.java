package com.kim.cloud.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new SaInterceptor(handler -> {
                            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
                            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                            SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
                            SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
                            SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
                        })
                ).addPathPatterns("/**");
    }
}

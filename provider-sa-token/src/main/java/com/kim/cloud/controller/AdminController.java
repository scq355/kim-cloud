package com.kim.cloud.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // 仅管理员角色可访问
    @SaCheckRole("admin")
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "欢迎进入管理员控制台";
    }
}

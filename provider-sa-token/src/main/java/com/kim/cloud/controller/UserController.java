package com.kim.cloud.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // 查询用户信息，需登录
    @GetMapping("/info")
    public String getUserInfo() {
        // 校验是否登录
        StpUtil.checkLogin();
        // 获取用户ID
        int userId = StpUtil.getLoginIdAsInt();
        return "当前用户信息，ID：" + userId;
    }

    // 修改用户信息，需有权限"user:update"
    @SaCheckPermission("user:update")
    @PostMapping("/update")
    public String updateUser() {
        return "用户信息更新成功";
    }
}

package com.kim.cloud.service;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
    // 返回一个用户所拥有的权限码集合
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        // 模拟从数据库获取权限
        List<String> permissionList = new ArrayList<>();
        if("10001".equals(loginId.toString())) {
            permissionList.add("user:update");
            permissionList.add("user:delete");
        }
        return permissionList;
    }

    // 返回一个用户所拥有的角色标识集合 (权限与角色可分开校验)
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        // 模拟从数据库获取角色
        List<String> roleList = new ArrayList<>();
        if("10001".equals(loginId.toString())) {
            roleList.add("admin");
        }
        return roleList;
    }
}

package com.kim.cloud.service.impl;

import com.kim.cloud.entities.User;
import com.kim.cloud.mapper.UserMapper;
import com.kim.cloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    @Transactional
    public User login(User user) {
        User dbUser = userMapper.login(user);
        if (dbUser == null) {
            throw new RuntimeException("登录失败");
        }
        return dbUser;
    }
}

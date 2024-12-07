package com.kim.cloud.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kim.cloud.security.entity.LoginUser;
import com.kim.cloud.security.entity.User;
import com.kim.cloud.security.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.eq("user_name", username);
                return Optional.ofNullable(userMapper.selectOne(wrapper))
                        .map(LoginUser::new)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}

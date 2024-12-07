package com.kim.cloud.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kim.cloud.security.common.JwtAuthenticationResponse;
import com.kim.cloud.security.common.SignUpRequest;
import com.kim.cloud.security.common.SigninRequest;
import com.kim.cloud.security.entity.LoginUser;
import com.kim.cloud.security.entity.Role;
import com.kim.cloud.security.entity.User;
import com.kim.cloud.security.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder()
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER).build();
        userMapper.insert(user);
        var jwt = jwtService.generateToken(new LoginUser(user));
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", request.getEmail());
        var user = Optional.of(userMapper.selectOne(wrapper))
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(new LoginUser(user));
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
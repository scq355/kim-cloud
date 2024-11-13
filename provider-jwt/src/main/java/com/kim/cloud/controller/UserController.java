package com.kim.cloud.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kim.cloud.entities.User;
import com.kim.cloud.service.UserService;
import com.kim.cloud.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public Map<String, Object> login(User user) {
        log.info("username={}, password={}", user.getName(), user.getPassword());
        HashMap<String, Object> map = new HashMap<>();
        try {
            User dbUser = userService.login(user);
            HashMap<String, String> payload = new HashMap<>();
            payload.put("id", dbUser.getId());
            payload.put("name", dbUser.getName());
            String token = JWTUtil.createToken(payload);
            map.put("token", token);
            map.put("state", true);
            map.put("msg", "认证成功");
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }


    @PostMapping("/user/test")
    public Map<String, Object> test(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        DecodedJWT decodedJWT = JWTUtil.verify(token);
        String userid = decodedJWT.getClaim("id").asString();
        String name = decodedJWT.getClaim("name").asString();
        log.info("userid={}, name={}", userid, name);

        map.put("state", true);
        map.put("msg", "认证成功");
        return map;
    }

}

package com.kim.cloud.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.cloud.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.SignatureException;
import java.util.HashMap;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        HashMap<String, Object> map = new HashMap<>();
        try {
            JWTUtil.verify(token);
            return true;
        } catch (AlgorithmMismatchException e) {
            log.error("签名算法匹配异常", e);
            map.put("msg", "签名算法匹配异常");
        } catch (TokenExpiredException e) {
            log.error("登录过期异常", e);
            map.put("msg", "登录过期异常");
        } catch (Exception e) {
            log.error("异常", e);
            map.put("msg", "token无效");
        }
        map.put("state", false);

        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(json);

        return false;
    }
}

package com.kim.cloud.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    private static final String SIGN = "267F^&f673&^fFSA#rh7876T*(!g&";

    /**
     * 生成token
     */
    public static String createToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 7);
        HashMap<String, Object> headMap = new HashMap<>();
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder
                .withHeader(headMap)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

}

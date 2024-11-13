package com.kim.cloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {

    @Test
    public void testApp() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 180);
        HashMap<String, Object> headMap = new HashMap<>();
        String token = JWT.create()
                .withHeader(headMap)
                .withClaim("username", "scq")
                .withClaim("userid", 123)
                .withClaim("role", "admin")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("nwe5190!@#%&54%sdfn%%^#$^w7"));
        System.out.println(token);
    }

    @Test
    public void testVerify(){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("nwe5190!@#%&54%sdfn%%^#$^w7")).build();
        DecodedJWT decodedJWT = verifier.verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNjcSIsInVzZXJpZCI6MTIzLCJyb2xlIjoiYWRtaW4iLCJleHAiOjE3MzE0NzkyMDd9.jn4h4XJougIjno-JBU6BKaur7XlUPaRhshWpShvEC6I");
        System.out.println(decodedJWT.getHeader());
        System.out.println(decodedJWT.getClaims().get("username"));
        System.out.println(decodedJWT.getClaim("userid"));
        System.out.println(decodedJWT.getSignature());
    }
}

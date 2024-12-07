package com.kim.cloud.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kim.cloud.security.mapper")
@SpringBootApplication
public class ProviderJwtSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderJwtSecurityApplication.class, args);
    }

}

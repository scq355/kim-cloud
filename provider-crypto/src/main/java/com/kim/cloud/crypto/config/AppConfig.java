package com.kim.cloud.crypto.config;

import cn.hutool.crypto.symmetric.AES;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class AppConfig {
    @Resource
    private CryptConfig cryptConfig;

    @Bean
    public AES aes() {
        return new AES(cryptConfig.getMode(),
                cryptConfig.getPadding(),
                cryptConfig.getKey().getBytes(StandardCharsets.UTF_8)
        );
    }
}

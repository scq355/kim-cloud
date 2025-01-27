package com.kim.cloud.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.dao.SaTokenDaoRedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenDaoConfig {

    @Bean
    public SaTokenDao saTokenDao() {
        return new SaTokenDaoRedis();
    }
}

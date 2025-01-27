package com.kim.cloud.crypto.config;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "crypto")
@PropertySource("classpath:crypto.properties")
@Data
@EqualsAndHashCode
public class CryptConfig {

    private Mode mode;
    private Padding padding;
    private String key;
    private String iv;
}

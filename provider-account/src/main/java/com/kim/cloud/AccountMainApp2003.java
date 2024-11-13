package com.kim.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 */
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.kim.cloud.mapper") //import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
public class AccountMainApp2003 {
    public static void main(String[] args) {
        SpringApplication.run(AccountMainApp2003.class, args);
    }
}

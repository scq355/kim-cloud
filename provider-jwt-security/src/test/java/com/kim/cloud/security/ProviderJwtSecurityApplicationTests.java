package com.kim.cloud.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kim.cloud.security.entity.User;
import com.kim.cloud.security.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ProviderJwtSecurityApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "zhangsan");

        //查询登录的用户信息是否存在
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }


    //可以通过注入直接使用
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder(){

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //通过encode方法可以进行加密
        String password1 = passwordEncoder.encode("scq355");
        String password2 = passwordEncoder.encode("scq355");

        //同一个明文加密多次结果是不同的
        System.out.println(password1);
        System.out.println(password2);

        //明文和密码校验，模拟登录
        boolean flage = passwordEncoder.matches(
                "scq355",
                "$2a$10$zVNznp4BdRNlYqfdrQLZIOPsbq8GWeU2HTyqL.FRfyvIQUYDfHxDe");

        System.out.println(flage);
    }

}

package com.kim.cloud;

import cn.hutool.json.JSONUtil;
import com.kim.cloud.crypto.common.Result;
import com.kim.cloud.crypto.utils.AESUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {
    @Test
    public void test1() {
        System.out.println(AESUtil.getAes());
        String data = JSONUtil.toJsonStr(new Result<String>());
        System.out.println(AESUtil.encryptHex(data));
        System.out.println(AESUtil.decrypt(AESUtil.encryptHex(data)));

        System.out.println(AESUtil.decrypt("fb2d3f66375f5359e1da75ae18a64a26c6c5d21108929217453601afe1e5308e9fdcb37baabb15d9c21952c23eb748cb3fa6538a535454c31b69afa3794cc42dd98c2579db7cfd45dbbf8e459eeac15fe006e9d96a4dc842255feb2f289fa727"));

        System.out.println(AESUtil.encryptHex("{\"name\":\"scq\",\"age\":10,\"birthday\":\"1992-10-11 12:10:00\",\"currentTimeMillis\":1737980068558}"));
    }
}

package com.kim.cloud.crypto.controller;

import com.kim.cloud.crypto.annotation.DecryptionAnnotation;
import com.kim.cloud.crypto.annotation.EncryptionAnnotation;
import com.kim.cloud.crypto.common.Result;
import com.kim.cloud.crypto.common.ResultBuilder;
import com.kim.cloud.crypto.entity.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ResultBuilder {

    /**
     * 直接返回对象，不加密
     * @param teacher Teacher对象
     * @return 不加密的对象
     */
    @PostMapping("/get")
    public ResponseEntity<Result<?>> get(@Validated @RequestBody Teacher teacher) {
        return success(teacher);
    }

    /**
     * 请求解密，响应加密
     * @param teacher Teacher对象
     * @return 返回加密后的数据 ResponseBody<Result>格式
     */
    @PostMapping("/encrypt")
    @EncryptionAnnotation
    public ResponseEntity<Result<?>> encrypt(@Validated @RequestBody Teacher teacher) {
        return success(teacher);
    }

    /**
     * 请求解密，响应加密
     * @param teacher Teacher对象
     * @return 返回加密后的数据 Result格式
     */
    @PostMapping("/encrypt1")
    @EncryptionAnnotation
    public Result<?> encrypt1(@Validated @RequestBody Teacher teacher) {
        return success(teacher).getBody();
    }

    /**
     * 请求加密，响应解密
     * @param teacher Teacher对象
     * @return 返回解密后的数据
     */
    @PostMapping("/decrypt")
    @DecryptionAnnotation
    public ResponseEntity<Result<?>> decrypt(@Validated @RequestBody Teacher teacher) {
        return success(teacher);
    }
}

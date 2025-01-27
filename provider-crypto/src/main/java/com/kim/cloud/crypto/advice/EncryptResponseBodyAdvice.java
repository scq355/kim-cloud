package com.kim.cloud.crypto.advice;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.cloud.crypto.annotation.EncryptionAnnotation;
import com.kim.cloud.crypto.common.Result;
import com.kim.cloud.crypto.entity.RequestBase;
import com.kim.cloud.crypto.exception.CryptoException;
import com.kim.cloud.crypto.utils.AESUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

/**
 * @description: responseBody自动解密
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Result<?>> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果直接是Result并且有解密注解，则处理
        return returnType.hasMethodAnnotation(EncryptionAnnotation.class);
    }

    @SneakyThrows
    @Override
    public Result<?> beforeBodyWrite(Result<?> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 真实数据
        Object data = body.getData();

        // 如果data为空，直接返回
        if (data == null) {
            return body;
        }

        // 如果是实体，并且继承了Request，则放入时间戳
        if (data instanceof RequestBase) {
            ((RequestBase)data).setCurrentTimeMillis(System.currentTimeMillis());
        }

        String dataText = JSONUtil.toJsonStr(data);

        // 如果data为空，直接返回
        if (StringUtils.isBlank(dataText)) {
            return body;
        }

        // 如果位数小于16，报错
        if (dataText.length() < 16) {
            throw new CryptoException("加密失败，数据小于16位");
        }

        String encryptText = AESUtil.encryptHex(dataText);

        return Result.builder()
                .status(body.getStatus())
                .data(encryptText)
                .message(body.getMessage())
                .build();
    }
}

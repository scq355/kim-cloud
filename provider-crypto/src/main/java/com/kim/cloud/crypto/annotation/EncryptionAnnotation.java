package com.kim.cloud.crypto.annotation;

import java.lang.annotation.*;

/**
 * @description: 加密生成类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptionAnnotation {
}

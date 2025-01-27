package com.kim.cloud.crypto.exception;

/**
 * @description: 自定义异常
 * @date : Created in 2022/2/4 11:09
 */
public class CryptoException extends CustomizeException {

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoException(String message) {
        super(message);
    }

}

package com.kim.cloud.crypto.common;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @description 返回值构造
 */
public interface ResultBuilder {

    /**
     * 成功的构造
     * @param data 数据
     * @return Result
     */
    default ResponseEntity<Result<?>> success(Object data) {
        return ResponseEntity.ok(Result.builder()
                .status(HttpStatus.OK.value()).data(data)
                .build());
    }

    /**
     * 400的构造
     * @param errorMsg 错误信息
     * @return Result
     */
    default ResponseEntity<Result<?>> badRequest(String errorMsg) {
        return ResponseEntity.badRequest().body(Result.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMsg)
                .build());
    }

    /**
     * 404的构造
     * @param errorMsg 错误信息
     * @return Result
     */
    default ResponseEntity<Result<?>> notFound(String errorMsg) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(Result.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(errorMsg)
                        .build());
    }

    /**
     * 500的构造
     * @param errorMsg 错误信息
     * @return Result
     */
    default ResponseEntity<Result<?>> internalServerError(String errorMsg) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(Result.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(errorMsg)
                        .build());
    }

}

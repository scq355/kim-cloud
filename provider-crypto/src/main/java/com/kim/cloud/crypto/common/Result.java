package com.kim.cloud.crypto.common;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Result<T> {

    @Builder.Default
    private int status = HttpStatus.OK.value();
    private T data;
    @Builder.Default
    private String message = "成功";

}

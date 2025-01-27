package com.kim.cloud.crypto.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RequestData implements Serializable {

    // 加密的文本
    private String text;

}
package com.match.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 响应结果状态枚举类
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResultStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功！"),

    /**
     * 参参数类型不匹配错误
     */
    PARAMETER_NOT_MATCHING(400, "参数类型不匹配错误");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;
}


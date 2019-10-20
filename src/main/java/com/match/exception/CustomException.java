package com.match.exception;

import com.match.entity.ResultStatusEnum;
import lombok.Getter;

/**
 * 自定制异常类
 */
@Getter
public class CustomException extends RuntimeException {
    private int code;
    private String message;

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(ResultStatusEnum resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
    }
}


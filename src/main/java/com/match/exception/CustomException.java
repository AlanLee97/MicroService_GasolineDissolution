package com.match.exception;

import com.match.entity.Data;
import lombok.Getter;

/**
 * 自定制异常类
 */
@Getter
public class CustomException extends RuntimeException {
    private int code;
    private String msg;
    private Data data;

    public CustomException(int code, String msg, Data data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CustomException(ResultStatusEnum resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.msg = resultStatusEnum.getMsg();
        this.data = resultStatusEnum.getData();
    }
}


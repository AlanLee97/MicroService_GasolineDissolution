package com.match.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Map<String, Object> handleCustomException(CustomException customException) {
        Map<String, Object> errorResultMap = new HashMap<>();
        errorResultMap.put("code", customException.getCode());
        errorResultMap.put("msg", customException.getMessage());

        Map<Object, Object> data = new HashMap<>();
        data.put("rs",customException.getData().getRs());
        data.put("note", customException.getData().getNote());
        errorResultMap.put("data", data);
        return errorResultMap;
    }
}


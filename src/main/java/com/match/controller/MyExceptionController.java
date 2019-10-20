package com.match.controller;

import com.match.exception.CustomException;
import com.match.exception.ResultStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionController {
    @ResponseBody
    @ExceptionHandler(value=NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex)
    {
        Map<String, Object> map = handleCustomException(new CustomException(ResultStatusEnum.PARAMETER_NOT_MATCHING));
        return new ResponseEntity(map, HttpStatus.valueOf(500));
    }

    public Map<String, Object> handleCustomException(CustomException customException) {
        Map<String, Object> errorResultMap = new HashMap<>(16);
        errorResultMap.put("code", customException.getCode());
        errorResultMap.put("message", customException.getMessage());
        return errorResultMap;
    }
}

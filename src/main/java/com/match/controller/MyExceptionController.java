package com.match.controller;

import com.match.exception.CustomException;
import com.match.exception.ResultStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionController {

    public Map<String, Object> handleCustomException(CustomException customException) {
        Map<String, Object> errorResultMap = new HashMap<>();
        errorResultMap.put("code", customException.getCode());
        errorResultMap.put("msg", customException.getMsg());
        Map<Object, Object> data = new HashMap<>();
        data.put("rs",customException.getData().getRs());
        data.put("note", customException.getData().getNote());
        errorResultMap.put("data", data);

        return errorResultMap;
    }


    /**
     * 处理数字类型转换异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value=NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
        Map<String, Object> map = handleCustomException(new CustomException(ResultStatusEnum.PARAMETER_NOT_MATCHING));
        return new ResponseEntity(map, HttpStatus.valueOf(200));
    }


    /**
     * 处理缺少参数异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        Map<String, Object> map = handleCustomException(new CustomException(ResultStatusEnum.MISSING_SERVLET_REQUEST_PARAMETER));
        map.put("msg", map.get("msg") + paramName);
        return new ResponseEntity(map, HttpStatus.valueOf(200));
    }
}

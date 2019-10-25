package com.match.exception;

import com.match.entity.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.MissingServletRequestParameterException;

/**
 * 响应结果状态枚举类
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResultStatusEnum {

    //缺少参数错误
    MISSING_SERVLET_REQUEST_PARAMETER(400, "错误，缺少参数", new Data(null, "出现错误，无结果")),

    //参数非数字类型错误
    PARAMETER_NOT_MATCHING(400, "错误，参数非数字类型错误", new Data(null, "出现错误，无结果"));



    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private Data data;
}


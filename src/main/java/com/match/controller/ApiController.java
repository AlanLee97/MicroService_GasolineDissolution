package com.match.controller;

import com.match.exception.ParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ApiController {
    /**
     * 返回api文档说明页面
     * @return
     */
    @RequestMapping("/api")
    public ModelAndView apiView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/api");
        return mv;
    }

    /**
     * 测试自定义异常
     */
    @RequestMapping("/test")
    public void test(){
        throw new ParameterException();
    }

}

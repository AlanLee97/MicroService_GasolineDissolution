package com.match.controller;

import com.match.entity.ResultStatusEnum;
import com.match.exception.CustomException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ApiController {
    @RequestMapping("/api")
    public ModelAndView apiView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/api");
        return mv;
    }

    @RequestMapping("/test")
    public void test(){
        throw new CustomException(ResultStatusEnum.PARAMETER_NOT_MATCHING);
    }

}

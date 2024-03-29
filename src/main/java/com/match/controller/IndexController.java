package com.match.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 官方的示例访问地址
 * http://106.74.152.35:14968/model/1/axletree/calculate?d=2000&α=30
 */
@RestController
public class IndexController {

    /**
     * 返回主页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView indexView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        return mv;
    }
}

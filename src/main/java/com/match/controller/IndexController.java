package com.match.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView indexView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        return mv;
    }
}

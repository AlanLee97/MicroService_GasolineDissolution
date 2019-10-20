package com.match.controller;

import com.match.entity.Api;
import com.match.entity.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CalController {
    @RequestMapping("/cal")
    public ModelAndView calView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cal");
        return mv;
    }

    /**
     * 返回接口数据的api
     * @param T 温度 单位K 取值范围273.15~333.15
     * @param P 绝对压力 单位Mpa 取值范围1.0~3.0
     * @param delta0 脱气原油对水的相对密度 取值0.8
     * @param deltag 天然气对空气的相对密度 取值范围0.58～0.62
     * @return Rs 溶解汽油比
     */
    @GetMapping("/rtnApi")
    @ResponseBody
    public Api calculate(double T, double P, double delta0, double deltag){

        //溶解汽油比
        double Rs = 0.0;

        //分子
        double molecular = 0.0;
        //分母
        double denominator = 0.0;

        //两个公式中相同的部分
        double temp1 = Math.log10((118.69 * P * deltag) / T + 0.891);
        double temp2 = 141.7 / delta0 - 131.5;

        //分子部分
        molecular = 19554.724 * temp1 * delta0;

        //分母部分
        denominator = ((0.826 * temp1 - 1) *
                (941.53982 / delta0 + 0.1747 *
                        Math.pow(temp2,2) - 0.0024 *
                        Math.pow(temp2,3) - 1496.3849));
        //结果
        Rs = molecular / denominator;

        System.out.println(Rs);
        String note = "溶解油气比";
        return new Api(200, "ok", new Data(Rs, note));
    }

}

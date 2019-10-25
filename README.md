
#  新型快速计算油气溶解比模型

##  介绍
- 模型公式来源于油气藏地质及开发工程国家重点实验室 （入选国家首批“双一流”世界一流学科“工程学” 和“地球科学”学科ESI排名进入全球大学和科研机构前1%）。天然气在原油中的溶解度是评价油气溶解性的重要参数之一，在工程上需查取图版而得到，结果不够准确、使用不太方便，不适于现场快速应用。此公式新模型，只需要代入绝对压力、温度、脱气原油对水的相对密度以及天然气对空气的相对密度 4 个已知参数，通过计算机便可快速计算出溶解油气比。

### 概要
![T ：温度：单位K，取值范围273.15~333.15 —————————————————————————P：绝对压力：单位MPa，取值范围1.0~3.0  —————————————————————————  Δg：天然气对空气的相对密度：天然气对空气的相对密度，取值范围0.58~0.62 —————————-- Δ0：脱气原油对水的相对密度：取值0.8———————————————————————---———](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy8xOTg4MDQ0Ny02ZGQyOTUwYjM4MTRhMDAzLnBuZw?x-oss-process=image/format,png)

参考文献： http://www.wanfangdata.com.cn/details/detail.do?_type=perio&id=trqgy201904013 
- 上述公式通过我们的微服务程序计算并返回json数据

### 项目特性
###### 该项目使用Spring Boot开发
- Starter依赖将所需的常见依赖按组聚集在一起，形成单条依赖

- maven引入依赖：

  - spring-boot-starter-web

  - Lombok

  - Spring Cloud
  
  - Thymeleaf
    

## 必要条件
- 开发环境：IntelliJ IDEA，JDK1.8，Tomcat8.5，使用Maven搭建Spring Boot 2.1.9 RELEASE框架

##  用法
1. 在浏览器输入网址：[ http://localhost:8080/index ]进入主页面
![公式运用原理 + 按钮：去计算](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy8xOTg4MDQ0Ny05NTc5MTMxMmY4ZjNiZDAxLnBuZw?x-oss-process=image/format,png)点击 “ 去计算 ” 按钮，进入计算页面
2. 在左下方输入对应的数据 ![计算页面](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy8xOTg4MDQ0Ny00ZDVkNmRiOGNjYjM2MDIzLnBuZw?x-oss-process=image/format,png)点击“ 计算 ” 按钮，弹出弹窗并显示结果![弹窗显示](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy8xOTg4MDQ0Ny00Zjg1ZjZlYzczNzAwN2E5LnBuZw?x-oss-process=image/format,png)
3. 点击上方导航栏“ Api ”，进入api的使用说明文档
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191025091913568.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxMTQ0NTY1NDU3Ng==,size_16,color_FFFFFF,t_70)





4. 返回json数据

   在浏览器里输入如下地址：

      http://localhost:8080/calculate?T=273.15&P=1.0&deltag=0.6&delta0=0.8
   

   

   返回的json数据：

   ```js
   {
       "code":200,
       "msg":"ok",
        "data":{
            "note":"溶解油气比",
            "rs":4.8669238391386
        }
   }
   ```



5. 核心代码

   ##### 计算
   
```java
@RestController
public class CalController {
    /**
     * 返回计算页面
     * @return
     */
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
    @RequestMapping(value = "/calculate",
            method = RequestMethod.GET,
            produces="application/json; charset=UTF-8")
    @ResponseBody
    public Api calculate(@RequestParam("T") double T,
                         @RequestParam("P") double P,
                         @RequestParam("delta0") double delta0,
                         @RequestParam("deltag") double deltag){

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
   
```



   ##### 异常处理

```java
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
```



```java
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

```


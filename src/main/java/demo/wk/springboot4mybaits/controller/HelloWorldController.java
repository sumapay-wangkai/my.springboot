package demo.wk.springboot4mybaits.controller;

import demo.wk.springboot4mybaits.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloWorldController
 * @Description TODO
 * @Author wangkai60
 * @Date 2018/12/19 11:29
 * @Version 1.0
 **/
@RestController
public class HelloWorldController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/hello")
    public String index() {
        return demoService.helloWorld();
    }
}
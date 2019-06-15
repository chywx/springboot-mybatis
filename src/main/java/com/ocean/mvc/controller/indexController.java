package com.ocean.mvc.controller;

import com.ocean.mvc.entity.Wolf;
import com.ocean.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个controller没什么用处，就当是测试了
 */
@Controller
public class indexController {

    @RequestMapping({"/index.do"})
    public String index(){
        System.out.println(123);
        System.out.println("chy");
        return "index";
    }


    @RequestMapping("testControllerAdvice1.do")
    @ResponseBody
    public String testControllerAdvice1(){
        int i = 1/0;
        return "hello";
    }

    @RequestMapping("testControllerAdvice2.do")
    public String testControllerAdvice2(ModelMap map){
        System.out.println(map.get("author"));
        int i = 1/0;
        return "world";
    }

//    http://localhost:8081/testFreemarker
    @RequestMapping("testFreemarker")
    public String index(ModelMap modelMap, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","dahai");
        modelMap.put("map",map);
        modelMap.put("city","北京");
        return "index";
    }

    @RequestMapping("/handsontable/demo1.do")
    public String demo1(){
        System.out.println("demo1");
        return "handsontable/demo1";
    }

    @RequestMapping("/angular/test1.do")
    public String test1(){
        return "angular/test1";
    }

}

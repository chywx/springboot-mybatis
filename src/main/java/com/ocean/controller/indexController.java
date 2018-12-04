package com.ocean.controller;

import com.ocean.entity.Wolf;
import com.ocean.service.WolfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class indexController {

    @RequestMapping({"/index.do","/"})
    public String index(){
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

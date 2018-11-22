package com.ocean.controller;

import com.ocean.entity.Wolf;
import com.ocean.service.WolfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wolf")
public class WolfController {

    @Autowired
    private WolfService service;

    @ResponseBody
    @RequestMapping("/test1.do")
    public Wolf test1(){
        Wolf wolf = new Wolf();
        wolf.setId(1);
        wolf.setWolfName("chy");
        wolf.setAge(22);
        return wolf;
    }

    @ResponseBody
    @RequestMapping("/queryAll.do")
    public List<Wolf> queryAll(){
        List<Wolf> list = service.queryAll();
        return list;
    }

}

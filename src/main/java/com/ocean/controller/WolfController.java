package com.ocean.controller;

import com.ocean.entity.Wolf;
import com.ocean.service.WolfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("queryAll");
        List<Wolf> list = service.queryAll();
        System.out.println(list);
        return list;
    }

    @ResponseBody
    @RequestMapping("/{wolfName}/queryByName.do")
    public Wolf queryByName(@PathVariable(value = "wolfName")String wolfName ){
        Wolf wolf = service.queryByName(wolfName);
        return  wolf;
    }

    @ResponseBody
    @RequestMapping("/queryByName2.do")
    public Wolf queryByName2(String wolfName ){
        Wolf wolf = service.queryByName(wolfName);
        return  wolf;
    }

    // 测试存储过程
    @ResponseBody
    @RequestMapping("/testProcedure.do")
    public Integer testProcedure(){
        System.out.println("testProcedure");
        Map<String,Integer> map=new HashMap<String, Integer>();
        map.put("sexid", 1);
        map.put("usercount", -1);
        HashMap hashMap = service.testProcedure(map);

        System.out.println(hashMap);
        return 100;
    }

}

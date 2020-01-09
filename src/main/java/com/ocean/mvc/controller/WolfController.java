package com.ocean.mvc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ocean.mvc.entity.Wolf;
import com.ocean.mvc.service.WolfService;
import com.ocean.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/wolf")
public class WolfController {

    @Autowired
    private WolfService service;

    @RequestMapping("/add1")
    @ResponseBody
    public String add1(@RequestBody Wolf wolf) {
        service.save1(wolf);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/test1.do")
    public Wolf test1() {
        Wolf wolf = new Wolf();
        wolf.setId(1);
        wolf.setWolfName("dahai");
        wolf.setAge(22);
        return wolf;
    }

    @ResponseBody
    @RequestMapping("/queryAll.do")
    public List<Wolf> queryAll() {
        System.out.println("queryAll");
        List<Wolf> list = service.queryAll();
        System.out.println(list);
        return list;
    }

    /**
     * PageHelper.startPage(currentPage, pageSize);仅对下一句sql有效，sql中limit不需要写
     * @param currentPage 当前页 1
     * @param pageSize    每页展示的数据量 5
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryByPageHelper.do")
    public PageInfo<Wolf> queryByPageHelper(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Wolf> list = service.queryAll();
        PageInfo<Wolf> wolfPageInfo = new PageInfo<>(list);
        return wolfPageInfo;
    }

    @ResponseBody
    @RequestMapping("/{wolfName}/queryByName.do")
    public Wolf queryByName(@PathVariable(value = "wolfName") String wolfName) {
        Wolf wolf = service.queryByName(wolfName);
        return wolf;
    }

    @ResponseBody
    @RequestMapping("/queryByName2.do")
    public Wolf queryByName2(String wolfName) {
        Wolf wolf = service.queryByName(wolfName);
        return wolf;
    }

    // 测试存储过程
    @ResponseBody
    @RequestMapping("/testProcedure.do")
    public Integer testProcedure() {
        System.out.println("testProcedure");
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("sexid", 1);
        map.put("usercount", -1);
        HashMap hashMap = service.testProcedure(map);

        System.out.println(hashMap);
        return 100;
    }

}

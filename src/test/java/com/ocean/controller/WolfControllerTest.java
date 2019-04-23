package com.ocean.controller;

import com.ocean.mvc.controller.WolfController;
import com.ocean.mvc.entity.Wolf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 测试controller有三种方式
 * 1. 跟测试service一样，注入进行测试
 * 2. 使用MockMvc模拟的HTTP请求
 * 3. 使用TestRestTemplate 完整HTTP请求测试，参考WolfControllerTest2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WolfControllerTest {

    private MockMvc mvc;

    @Autowired
    private WolfController wolfController;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void queryAll() {
        List<Wolf> wolves = wolfController.queryAll();
        wolves.forEach(System.out::println);
    }

    @Test
    public void queryByName() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wolf/chy/queryByName.do")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                //form表单格式传参
//                .param("id", "4")
//                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果

        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }



}
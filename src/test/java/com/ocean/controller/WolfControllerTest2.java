package com.ocean.controller;

import com.ocean.entity.Wolf;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@RunWith(SpringRunner.class)
// 不晓得为什么，必须要设置为随机端口
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WolfControllerTest2 {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void queryAll(){
        String url  = "/wolf/queryAll.do";
        // 封装入参数，不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("pageNum", 1);
        paramMap.add("pageSize", 5);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap,headers);


        // 1、使用postForObject请求接口
        List<Wolf> result = restTemplate.postForObject(url, paramMap, List.class);
        System.out.println("result1==================" + result);

        // 2、使用postForEntity请求接口
        ResponseEntity<List> result2 = restTemplate.postForEntity(url, httpEntity, List.class);
        System.out.println("result2====================" + result2.getBody());

//        // 3、使用exchange请求接口
//        //设置接受参数的类型 只有用exchange+ParameterizedTypeReference接收到的pageInfo中的List是user类，上面两种方式接收到的都是个LinkedList
        ParameterizedTypeReference<List<Wolf>> type = new ParameterizedTypeReference<List<Wolf>>() {};
        ResponseEntity<List<Wolf>> response3 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, type);
        System.out.println("result3====================" + response3.getBody());

//        Assert.assertThat(response3.getBody(), Matchers.notNullValue());

    }

    @Test
    public void queryByName2(){
        String url  = "/wolf/queryByName2.do";
        // 封装入参数，不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("wolfName", "wx");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap,headers);
        // 1、使用postForObject请求接口
        Wolf result = restTemplate.postForObject(url, paramMap, Wolf.class);
        System.out.println("result==================" + result);
    }

}

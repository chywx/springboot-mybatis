package com.ocean.service;

import com.ocean.mvc.entity.Wolf;
import com.ocean.redis.RedisService;
import com.ocean.redis.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisServiceImpl redisService;

    @Test
    public void test() throws Exception {
//        基本写法
//        stringRedisTemplate.opsForValue().set("aaa","111");
//        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
//        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        Wolf wolf = new Wolf();
        wolf.setId(23);
        wolf.setWolfName("不会打篮球的程序不是好男人");
        redisService.valuePut("aaa", wolf);
        System.out.println(redisService.getValue("aaa"));
    }


}

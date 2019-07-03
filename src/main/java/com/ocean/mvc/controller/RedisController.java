package com.ocean.mvc.controller;

import com.ocean.mvc.entity.Wolf;
import com.ocean.redis.RedisService;
import com.ocean.redis.lock.RedisDistributionLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("redis")
@RestController
public class RedisController {

    private static final String LOCK_NO = "redis_distribution_lockno";

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisDistributionLock redisLock;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("testRedis")
    public Wolf testRedis() {
        Wolf wolf = new Wolf();
        wolf.setId(23);
        wolf.setWolfName("不会打篮球的程序不是好男人");
        redisService.valuePut("aaa", wolf);
        System.out.println();
        return (Wolf) redisService.getValue("aaa");
    }

    /**
     * 待测试，有些忘了
     *
     * @return
     */
    @GetMapping("testLock")
    public String testLock() {
        Long lockTime;
        boolean flag = true;
        if ((lockTime = redisLock.lock("lock2", "quesheng")) != 0) {
            System.out.println("进来了");
            if (flag == true) {
                flag = false;
                redisLock.unlock("lock2", lockTime, "quesheng");
                return "flag为true";
            } else {
                redisLock.unlock("lock2", lockTime, "quesheng");
                return "flag为false";
            }
        } else {
            return lockTime + "";
        }
    }

}

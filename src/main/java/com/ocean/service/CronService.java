package com.ocean.service;

import java.util.Date;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author chy
 * @date 2021年10月08日 下午 17:00
 */
@Service
@EnableScheduling
public class CronService {

    @Scheduled(cron = "0 */1 * * * ?")
    public void oneMinute() {
        System.out.println("current time: " + new Date());
    }
}

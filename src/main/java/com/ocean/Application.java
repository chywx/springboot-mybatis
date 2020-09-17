package com.ocean;

import cn.chendahai.dingding.service.DingdingService;
import com.github.pagehelper.PageInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    //配置mybatis的分页插件pageHelper
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable", "false");
//        properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
//        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public DingdingService dingdingService() {
        return new DingdingService();
    }

}

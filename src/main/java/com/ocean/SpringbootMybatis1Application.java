package com.ocean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ocean.controller","com.ocean.service","com.email"})
public class SpringbootMybatis1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatis1Application.class, args);
    }

}

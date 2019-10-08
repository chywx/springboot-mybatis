package com.ocean.service;

import com.ocean.mvc.dao.WolfDao;
import com.ocean.mvc.entity.Wolf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WolfServiceTest {


    @Autowired
    private WolfDao dao;

    @Test
    public void queryAll() {
        List<Wolf> all = dao.queryAll();
        all.forEach(System.out::println);
    }

    @Test
    public void queryByName() {
        Wolf chy = dao.queryByName("chy");
        System.out.println(chy);
    }
}
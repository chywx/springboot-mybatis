package com.ocean.service;

import com.ocean.dao.WolfDao;
import com.ocean.entity.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WolfService {
    @Autowired
    private WolfDao dao;

    public List<Wolf> queryAll() {
        return dao.queryAll();
    }
}

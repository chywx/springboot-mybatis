package com.ocean.service;

import com.ocean.dao.WolfDao;
import com.ocean.entity.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WolfService {
    @Autowired
    private WolfDao dao;

    public List<Wolf> queryAll() {
        return dao.queryAll();
    }



    public HashMap testProcedure(Map<String, Integer> map) {
        return dao.testProcedure(map);
    }

    public Wolf queryByName(String wolfName) {
        return dao.queryByName(wolfName);
    }
}

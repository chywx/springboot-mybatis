package com.ocean.service;

import com.ocean.dao.WolfDao;
import com.ocean.entity.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public void save1(Wolf wolf){
        dao.save1(wolf);
        int i = 1/0;
    }

    public void save2(Wolf wolf){
        dao.save2(wolf);
    }




    public HashMap testProcedure(Map<String, Integer> map) {
        return dao.testProcedure(map);
    }

    public Wolf queryByName(String wolfName) {
        return dao.queryByName(wolfName);
    }
}

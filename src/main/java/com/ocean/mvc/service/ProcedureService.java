package com.ocean.mvc.service;

import com.ocean.mvc.dao.ProcedureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class ProcedureService {

    @Autowired
    private ProcedureDao dao;

    public void testProcedure(Map<String, Integer> parmeterMap) {
        dao.testProcedure(parmeterMap);
    }
}

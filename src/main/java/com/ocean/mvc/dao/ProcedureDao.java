package com.ocean.mvc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Mapper
public interface ProcedureDao {
    void testProcedure(Map<String, Integer> parmeterMap);
}

package com.ocean.dao;

import com.ocean.entity.Wolf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WolfDao{

    @Select("select id,wolf_name wolfName ,age from test.wolf")
    List<Wolf> queryAll();

}

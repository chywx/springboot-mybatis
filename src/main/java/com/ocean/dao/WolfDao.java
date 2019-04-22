package com.ocean.dao;

import com.ocean.entity.Wolf;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface WolfDao{

    @Select("select id,wolf_name wolfName ,age from github.wolf")
    List<Wolf> queryAll();

    @Select({"call github.ges_user_count(#{sexid,mode=IN,jdbcType=INTEGER},#{usercount,mode=OUT,jdbcType=INTEGER})"})
    @Options(statementType = StatementType.CALLABLE)
    HashMap testProcedure(Map<String, Integer> map);

    @Select("select id,wolf_name wolfName ,age from github.wolf where wolf_name = #{wolfName}")
    Wolf queryByName(@Param("wolfName") String wolfName);


    @Insert("insert into wolf (wolf_name,age) values (#{wolfName},#{age})")
    void save1(Wolf wolf);

    @Insert("insert into wolf (wolf_name,age) values (#{wolfName},#{age})")
    void save2(Wolf wolf);
}

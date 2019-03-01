package com.ocean.dao;

import com.ocean.entity.Wolf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface WolfDao{

    @Select("select id,wolf_name wolfName ,age from test.wolf")
    List<Wolf> queryAll();

    @Select({"call test.ges_user_count(#{sexid,mode=IN,jdbcType=INTEGER},#{usercount,mode=OUT,jdbcType=INTEGER})"})
    @Options(statementType = StatementType.CALLABLE)
    HashMap testProcedure(Map<String, Integer> map);

    @Select("select id,wolf_name wolfName ,age from test.wolf where wolf_name = #{wolfName}")
    Wolf queryByName(@Param("wolfName") String wolfName);
}

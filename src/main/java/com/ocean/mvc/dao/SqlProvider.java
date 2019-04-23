package com.ocean.mvc.dao;


import org.apache.ibatis.jdbc.SQL;

import static org.apache.ibatis.jdbc.SelectBuilder.*;

public class SqlProvider {
    public String selectUser(Integer userId){
        return "select id,name,age,sex from github.p_user where id = #{userId}";
    }
}

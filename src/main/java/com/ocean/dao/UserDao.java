package com.ocean.dao;

import com.ocean.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @SelectProvider(type = SqlProvider.class,method = "selectUser")
    public User findUserById(Integer userId);

}

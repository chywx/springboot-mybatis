package com.ocean.mvc.dao;

import com.ocean.mvc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @SelectProvider(type = SqlProvider.class,method = "selectUser")
    public User findUserById(Integer userId);

    int insertUser(User user);

}

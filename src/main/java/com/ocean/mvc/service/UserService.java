package com.ocean.mvc.service;

import com.ocean.mvc.dao.UserDao;
import com.ocean.mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findUserById(Integer userId){
        return userDao.findUserById(userId);
    }

    public int insertUser(User user){
        return userDao.insertUser(user);
    }
}

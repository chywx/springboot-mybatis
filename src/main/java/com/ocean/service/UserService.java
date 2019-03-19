package com.ocean.service;

import com.ocean.dao.UserDao;
import com.ocean.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findUserById(Integer userId){
        return userDao.findUserById(userId);
    }
}

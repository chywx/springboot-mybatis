package com.ocean.controller;

import com.ocean.entity.User;
import com.ocean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/findUserById.do")
    public User findUserById(Integer userId){
        return userService.findUserById(userId);
    }
}

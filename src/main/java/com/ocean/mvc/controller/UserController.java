package com.ocean.mvc.controller;

import com.ocean.mvc.entity.Person;
import com.ocean.mvc.entity.PersonModel;
import com.ocean.mvc.entity.User;
import com.ocean.mvc.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findUserById.do")
    public User findUserById(Integer userId) {
        return userService.findUserById(userId);
    }

    @RequestMapping("/insertUser.do")
    public int insertUser(User user) {
        System.out.println("插入之前：" + user);
        int i = userService.insertUser(user);
        System.out.println("插入之后：" + user);
        return i;
    }

    @GetMapping("/testList")
    public Object testList(PersonModel personModel) {
        List<Person> persons = personModel.getPersons();
        System.out.println(persons);
        return persons;
    }

}

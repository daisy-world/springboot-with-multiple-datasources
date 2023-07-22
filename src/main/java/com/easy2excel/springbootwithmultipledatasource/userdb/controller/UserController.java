package com.easy2excel.springbootwithmultipledatasource.userdb.controller;

import com.easy2excel.springbootwithmultipledatasource.accountdb.entity.Account;
import com.easy2excel.springbootwithmultipledatasource.userdb.entity.User;
import com.easy2excel.springbootwithmultipledatasource.userdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("api/v1/user")
    public User saveUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping("api/v1/users")
    public List<User> getUserList(){
        return userRepository.findAll();

    }


}

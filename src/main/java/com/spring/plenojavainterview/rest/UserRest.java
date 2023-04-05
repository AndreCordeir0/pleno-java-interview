package com.spring.plenojavainterview.rest;

import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.security.UserServiceImpl;
import com.spring.plenojavainterview.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserRest {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@RequestBody User user){
        return userService.createNewUser(user);
    }
}

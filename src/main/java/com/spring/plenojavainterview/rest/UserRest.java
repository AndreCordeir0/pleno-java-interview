package com.spring.plenojavainterview.rest;

import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.security.UserServiceImpl;
import com.spring.plenojavainterview.service.impl.UserService;
import com.spring.plenojavainterview.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserRest {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@RequestBody User user){
        try{
            return userService.createNewUser(user);
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar usuario");
        }
    }

    @PostMapping(value = "/token",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String requestToken(@RequestPart String email,@RequestPart String senha){
        try {
            return userService.requestToken(email,senha);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao recuperar token");
        }
    }

    @PutMapping("/modify")
    public User modifyUser(@RequestBody User user, @RequestHeader("Authorization") String authorizationHeader){
        return userService.alterUser(user,TokenUtil.getTokenWithBearer(authorizationHeader));
    }
}

package com.spring.plenojavainterview.service.impl;

import com.spring.plenojavainterview.dao.RoleDAO;
import com.spring.plenojavainterview.dao.UserDAO;
import com.spring.plenojavainterview.dao.UserRoleDAO;
import com.spring.plenojavainterview.enums.RoleEnum;
import com.spring.plenojavainterview.model.Role;
import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.model.UserRole;
import com.spring.plenojavainterview.security.JwtUtil;
import com.spring.plenojavainterview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    JwtUtil jwtUtil;

    public String createNewUser(User user){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setSenha(bc.encode(user.getSenha()));
        userDAO.save(user);

        Role rolemodel = roleDAO.findById(3l).orElseThrow(()-> new RuntimeException());
        UserRole userRole = new UserRole();
        userRole.setRoleId(rolemodel);
        userRole.setRoleUser(user);

        userRoleDAO.save(userRole);
        return jwtUtil.generateToken(user.getNome());
    }



}

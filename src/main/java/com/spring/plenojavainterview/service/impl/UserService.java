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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
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
        createUserRole(user);
        userDAO.save(user);

        return jwtUtil.generateToken(user.getNome());
    }

    public String requestToken(String email, String senha){
        if(Objects.isNull(email) || Objects.isNull(senha)){
            throw new UsernameNotFoundException("Email ou senha incorretos");
        }
        User user = userDAO.findByUsername(email).orElseThrow(()->new UsernameNotFoundException("Email incorreto"));
        boolean isValido = matchPassword(senha,user.getSenha());
        if(isValido){
            return jwtUtil.generateToken(user.getEmail());
        }
        throw new UsernameNotFoundException("Email ou senha incorretos");
    }

    private boolean matchPassword(String passwordRequest, String passwordDataBase){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        if(bc.matches(passwordRequest,passwordDataBase)) return true;
        return false;
    }
    private void createUserRole(User user){
        Role rolemodel = roleDAO.findById(RoleEnum.USER.getRoleId()).orElseThrow(()-> new RuntimeException());
        UserRole userRole = new UserRole();
        userRole.setRoleId(rolemodel);
        userRole.setRoleUser(user);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRolesUser(userRoles);
    }
}

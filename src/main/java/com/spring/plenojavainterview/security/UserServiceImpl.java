package com.spring.plenojavainterview.security;

import com.spring.plenojavainterview.dao.UserDAO;
import com.spring.plenojavainterview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return UserDetailImpl.build(user);
    }
}

package com.spring.plenojavainterview.service;

import com.spring.plenojavainterview.dto.UserPaginationDTO;
import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.pageable.Pageable;

import java.util.List;

public interface IUserService {

    public String createNewUser(User user);
    public String requestToken(String email, String senha);

    public User alterUser(User user, String token);

    public List<UserPaginationDTO> listAllUsers(Pageable<UserPaginationDTO> paginacao);
}

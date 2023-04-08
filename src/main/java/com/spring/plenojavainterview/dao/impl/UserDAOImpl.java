package com.spring.plenojavainterview.dao.impl;

import com.spring.plenojavainterview.dto.UserPaginationDTO;
import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.pageable.Pageable;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class UserDAOImpl {
    @PersistenceContext
    EntityManager entityManager;


    public Pageable<UserPaginationDTO> listarUsuarios(Pageable<UserPaginationDTO> paginacao){
        Query query = entityManager.createQuery("select user FROM User user",User.class);
        query.setFirstResult(paginacao.getPageNumber() * paginacao.getPageSize());
        query.setMaxResults(paginacao.getPageSize());
        List<User> allUsers = query.getResultList();
        List<UserPaginationDTO> list = allUsers.stream().map(UserPaginationDTO::build).collect(Collectors.toList());
        paginacao.setLista(list);

        return paginacao;
    }
}

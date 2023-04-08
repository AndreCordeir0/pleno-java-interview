package com.spring.plenojavainterview.dao.impl;

import com.spring.plenojavainterview.dto.UserPaginationDTO;
import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.security.pageable.Pageable;


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
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT user FROM User user");
        Query query = entityManager.createQuery("select user FROM User user",User.class);
        query.setFirstResult(paginacao.getPageNumber() * paginacao.getPageSize());
        query.setMaxResults(paginacao.getPageSize());
        List<User> a = query.getResultList();
        List<UserPaginationDTO> lista = a.stream().map(us->new UserPaginationDTO(us)).collect(Collectors.toList());
        paginacao.setLista(lista);

        return paginacao;
    }
}

package com.spring.plenojavainterview.dao;

import com.spring.plenojavainterview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user LEFT JOIN FETCH user.rolesUser WHERE user.email = :email")
    public Optional<User> findByEmail(@Param("email")String email);

}

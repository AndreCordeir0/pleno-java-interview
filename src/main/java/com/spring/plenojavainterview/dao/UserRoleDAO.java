package com.spring.plenojavainterview.dao;

import com.spring.plenojavainterview.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
}

package com.spring.plenojavainterview.dao;

import com.spring.plenojavainterview.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role,Long> {
}

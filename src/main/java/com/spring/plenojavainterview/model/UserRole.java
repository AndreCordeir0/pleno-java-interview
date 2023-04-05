package com.spring.plenojavainterview.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @JoinColumn(name = "ROLE_ID")
    @ManyToOne
    private Role roleId;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User roleUser;

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public User getRoleUser() {
        return roleUser;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleUser(User roleUser) {
        this.roleUser = roleUser;
    }

}

package com.spring.plenojavainterview.enums;

public enum RoleEnum {

    ADMIN(1L),
    MODERATOR(2L),
    USER(3L);

    private Long roleId;
    RoleEnum(Long roleId){
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }
}

package com.spring.plenojavainterview.dto;

import com.spring.plenojavainterview.model.User;

public class UserPaginationDTO {

    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UserPaginationDTO(){

    }

    public static UserPaginationDTO build(User user){
        UserPaginationDTO userPaginationDTO = new UserPaginationDTO();
        userPaginationDTO.setId(user.getId());
        userPaginationDTO.setNome(user.getNome());
        return userPaginationDTO;
    }
}

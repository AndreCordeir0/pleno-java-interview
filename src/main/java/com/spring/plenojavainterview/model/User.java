package com.spring.plenojavainterview.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "TB_USUARIO")
public class User extends BaseModel{
    public User() {
    }
    @Column(length = 100)
    private String nome;

    private String email;

    private String senha;

    @OneToMany(mappedBy = "roleUser",cascade = CascadeType.PERSIST)
    private Set<UserRole> rolesUser;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<UserRole> getRolesUser() {
        return rolesUser;
    }

    public void setRolesUser(Set<UserRole> rolesUser) {
        this.rolesUser = rolesUser;
    }
}

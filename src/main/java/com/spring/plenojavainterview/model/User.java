package com.spring.plenojavainterview.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "TB_USUARIO")
public class User extends BaseModel{

    @Column(length = 100)
    private String nome;

    private String email;

    private String senha;

    private String telefone;

    @CreationTimestamp
    @Column(name = "DATA_CRIACAO")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

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
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    public User() {
    }
}

package com.spring.plenojavainterview.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.plenojavainterview.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailImpl implements UserDetails {

    private Long id;

    private String email;

    private String nome;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(Long id, String email,String nome, String password,Collection<? extends GrantedAuthority> authorities ){
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailImpl build(User user){
        Set<GrantedAuthority> authorities = user.getRolesUser().stream().map(e->new SimpleGrantedAuthority("ROLE_" + e.getRoleId().getRole().toString())).collect(Collectors.toSet());
        return new UserDetailImpl(
                user.getId(),
                user.getEmail(),
                user.getNome(),
                user.getSenha(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

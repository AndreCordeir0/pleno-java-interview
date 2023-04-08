package com.spring.plenojavainterview.service.impl;

import com.spring.plenojavainterview.dao.RoleDAO;
import com.spring.plenojavainterview.dao.UserDAO;
import com.spring.plenojavainterview.dao.UserRoleDAO;
import com.spring.plenojavainterview.dao.impl.UserDAOImpl;
import com.spring.plenojavainterview.dto.UserPaginationDTO;
import com.spring.plenojavainterview.enums.RoleEnum;
import com.spring.plenojavainterview.model.Role;
import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.model.UserRole;
import com.spring.plenojavainterview.pageable.Pageable;
import com.spring.plenojavainterview.security.JwtUtil;
import com.spring.plenojavainterview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserDAOImpl userDAOImpl;

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    JwtUtil jwtUtil;

    public String createNewUser(User user){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setSenha(bc.encode(user.getSenha()));
        createUserRole(user);
        userDAO.save(user);

        return jwtUtil.generateToken(user.getNome());
    }

    public String requestToken(String email, String senha){
        if(Objects.isNull(email) || Objects.isNull(senha)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ou senha incorretos");
        }
        User user = userDAO.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Email incorreto"));
        boolean isValido = matchPassword(senha,user.getSenha());
        if(isValido){
            return jwtUtil.generateToken(user.getEmail());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ou senha incorretos");
    }

    public User alterUser(User user, String token){
        if(Objects.isNull(token))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Você não tem acesso a essa funcionalidade");
        String email = jwtUtil.getUsuarioWithToken(token);
        User userDataBase = userDAO.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao recuperar usuario"));
        updateUser(userDataBase, user);
        userDAO.save(userDataBase);
        return user;
    }

    public List<UserPaginationDTO> listAllUsers(Pageable<UserPaginationDTO> paginacao){
        Pageable<UserPaginationDTO> listaUsuarios = userDAOImpl.listarUsuarios(paginacao);

        return listaUsuarios.getLista();
    }
    private void updateUser(User userDataBase, User userToAlter){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        userDataBase.setNome(userToAlter.getNome());
        userDataBase.setSenha(bc.encode(userToAlter.getSenha()));
        userDataBase.setTelefone(userToAlter.getTelefone());
        userDataBase.setEmail(userToAlter.getEmail());
    }
    private boolean matchPassword(String passwordRequest, String passwordDataBase){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        return bc.matches(passwordRequest, passwordDataBase);
    }
    private void createUserRole(User user){
        Role rolemodel = roleDAO.findById(RoleEnum.USER.getRoleId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao recuperar Roles"));
        UserRole userRole = new UserRole();
        userRole.setRoleId(rolemodel);
        userRole.setRoleUser(user);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRolesUser(userRoles);
    }
}

package com.spring.plenojavainterview.rest;

import com.spring.plenojavainterview.dto.UserPaginationDTO;
import com.spring.plenojavainterview.model.User;
import com.spring.plenojavainterview.pageable.Pageable;
import com.spring.plenojavainterview.service.impl.UserService;
import com.spring.plenojavainterview.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserRest {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public String createUser(@RequestBody User user){
        try{
            return userService.createNewUser(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"Erro ao salvar usuario");
        }
    }

    @PostMapping(value = "/token",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String requestToken(@RequestPart String email,@RequestPart String senha){
        try {
            return userService.requestToken(email,senha);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao recuperar token");
        }
    }

    @PutMapping("/modify")
    public User modifyUser(@RequestBody User user, @RequestHeader("Authorization") String authorizationHeader){
        return userService.alterUser(user,TokenUtil.getTokenWithBearer(authorizationHeader));
    }

    @GetMapping("/list-all")
    public List<UserPaginationDTO> listarUsuarios(    @RequestParam(required = true) int pageSize,
                                         @RequestParam(required = true) int pageNumber){
        Pageable<UserPaginationDTO> paginacao = new Pageable<>();
        paginacao.setPageNumber(pageNumber);
        paginacao.setPageSize(pageSize);
        return userService.listAllUsers(paginacao);
    }
}

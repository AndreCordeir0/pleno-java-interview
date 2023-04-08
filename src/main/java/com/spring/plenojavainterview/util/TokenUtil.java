package com.spring.plenojavainterview.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TokenUtil {

    //Classes de utilidades não devem permitir a criação de novas instancias
    private TokenUtil(){
        throw new IllegalStateException();
    }
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    public static String getTokenWithBearer(String authorizationHeader){
        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Header invalido");
        }
        return authorizationHeader.substring(7);
    }
}

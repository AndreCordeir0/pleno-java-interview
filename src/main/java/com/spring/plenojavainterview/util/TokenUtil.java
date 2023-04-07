package com.spring.plenojavainterview.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TokenUtil {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    public static String getTokenWithBearer(String authorizationHeader){
        String token = null;
        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Header invalido");
        }else{
            token = authorizationHeader.substring(7);
        }
        return token;
    }
}

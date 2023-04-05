package com.spring.plenojavainterview.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    public String getUsuarioWithToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validarToken(String token, UserDetails userDetails) {
        final String username = obterUsuario(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String username){
        Map<String, Object> claim = new HashMap<String, Object>();
        return generateTokenFromUsername(claim,username);
    }
    private Boolean isTokenExpired(String token) {
        final Date dataValidade = obterDataValidade(token);
        return dataValidade.before(new Date());
    }
    public Date obterDataValidade(String token) {
        return obterClaim(token, Claims::getExpiration);
    }
    private <T> T obterClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = obterClaims(token);
        return claimsResolver.apply(claims);

    }
    public String obterUsuario(String token) {
        return obterClaim(token, Claims::getSubject);
    }
    private Claims obterClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
    public String generateTokenFromUsername(Map<String, Object> claim , String username) {
        return Jwts.builder()
                .setClaims(claim)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}

package com.dc.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.access.token.expiration}")
    private Long accessTokenExpiration;

    @Value("${jwt.refresh.token.expiration}")
    private Long refreshTokenExpiration;

    private Key getKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String email, Boolean isAccessToken){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (isAccessToken ? accessTokenExpiration : refreshTokenExpiration)))
                .setSubject(email)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String email){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(email)
                    .getBody()
                    .getSubject();
        }
        catch (Exception e){
            return null;
        }
    }
}

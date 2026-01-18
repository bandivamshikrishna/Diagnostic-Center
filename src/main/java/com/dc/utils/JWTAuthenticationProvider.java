package com.dc.utils;

import com.dc.entity.UserAuthEntity;
import com.dc.exception.InvalidJWTException;
import com.dc.exception.UserNotFoundException;
import com.dc.serviceImpl.UserAuthServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.servlet.HandlerExceptionResolver;

public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JWTUtils jwtUtils;
    private final UserAuthServiceImpl userAuthService;


    public JWTAuthenticationProvider(JWTUtils jwtUtils, UserAuthServiceImpl userAuthService){
        this.jwtUtils = jwtUtils;
        this.userAuthService = userAuthService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JWTAuthenticationToken) authentication).getToken();

        String email = jwtUtils.getEmailFromToken(token);
        if(email == null){
            throw  new InvalidJWTException("Invalid JWT Token");
        }

        UserAuthEntity userAuthEntity = userAuthService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userAuthEntity,null,userAuthEntity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

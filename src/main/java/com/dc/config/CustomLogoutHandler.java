package com.dc.config;

import com.dc.exception.TokenNotFoundException;
import com.dc.repository.UserAuthTokenRepository;
import com.dc.service.UserAuthTokenService;
import com.dc.serviceImpl.UserAuthServiceImpl;
import com.dc.utils.JWTUtils;
import com.dc.utils.JWTValidationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final UserAuthTokenService userAuthTokenService;
    private final JWTUtils jwtUtils;


    public CustomLogoutHandler(UserAuthTokenService userAuthTokenServicer,JWTUtils jwtUtils){
        this.userAuthTokenService = userAuthTokenServicer;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String accessToken = JWTValidationFilter.getTokenFromRequest(request);
        if(accessToken == null)
            throw new TokenNotFoundException("Access Token is Missing");
        userAuthTokenService.invalidateOldRefreshTokens(jwtUtils.getEmailFromToken(accessToken));
    }
}

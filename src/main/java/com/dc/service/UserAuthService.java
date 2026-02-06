package com.dc.service;

import com.dc.dto.JWTTokens;
import com.dc.dto.UserCreateRequestDTO;
import com.dc.dto.UserLoginRequestDTO;
import com.dc.dto.UserResponseDTO;
import com.dc.entity.UserAuthEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface UserAuthService {
    public String createUser(UserCreateRequestDTO userCreateRequestDTO);
    public void setUserPassword(String token,String password);
    public JWTTokens loginUser(UserLoginRequestDTO userLoginRequestDTO);
    public UserResponseDTO getUserDetails(@AuthenticationPrincipal UserAuthEntity userAuthEntity);
}


package com.dc.service;

import com.dc.dto.JWTTokens;
import com.dc.dto.UserCreateRequestDTO;
import com.dc.dto.UserLoginRequestDTO;

public interface UserAuthService {
    public String createUser(UserCreateRequestDTO userCreateRequestDTO);
    public void setUserPassword(String token,String password);
    public JWTTokens loginUser(UserLoginRequestDTO userLoginRequestDTO);
}


package com.dc.service;

import com.dc.entity.UserAuthEntity;

public interface UserAuthTokenService {
    public void createToken(UserAuthEntity userAuthEntity);
    public Boolean validateToken(String token);
    public UserAuthEntity getUserFromToken(String token);
    public void updateTokenUsed(String token);
}

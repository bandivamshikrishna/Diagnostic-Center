package com.dc.service;

import com.dc.entity.UserAuthEntity;
import com.dc.enums.TokenTypeEnum;

public interface UserAuthTokenService {
    public void createToken(UserAuthEntity userAuthEntity, String token, TokenTypeEnum tokenType, Long minutes);
    public Boolean validateToken(String token);
    public UserAuthEntity getUserFromToken(String token);
    public void updateTokenUsed(String token);
    public void invalidateOldRefreshTokens(String email);
}

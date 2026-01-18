package com.dc.serviceImpl;

import com.dc.entity.UserAuthEntity;
import com.dc.entity.UserAuthTokenEntity;
import com.dc.exception.TokenAlreadyUsedExpection;
import com.dc.exception.TokenNotFoundException;
import com.dc.repository.UserAuthTokenRepository;
import com.dc.service.UserAuthTokenService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserAuthTokenServiceImpl implements UserAuthTokenService {

    private final UserAuthTokenRepository userAuthTokenRepository;

    public UserAuthTokenServiceImpl(UserAuthTokenRepository userAuthTokenRepository){
        this.userAuthTokenRepository = userAuthTokenRepository;
    }

    @Override
    public void createToken(UserAuthEntity userAuthEntity) {
        UserAuthTokenEntity userAuthTokenEntity = new UserAuthTokenEntity();
        userAuthTokenEntity.setToken(UUID.randomUUID().toString());
        userAuthTokenEntity.setUser(userAuthEntity);
        userAuthTokenEntity.setTokenCreatedDate(LocalDateTime.now());
        userAuthTokenEntity.setTokenUsed(false);
        userAuthTokenEntity.setTokenExpirationDate(LocalDateTime.now().plusMinutes(30));
        userAuthTokenRepository.save(userAuthTokenEntity);
    }

    @Override
    public Boolean validateToken(String token) {
        UserAuthTokenEntity userAuthTokenEntity = userAuthTokenRepository.findByToken(token).orElseThrow(
                ()-> new TokenNotFoundException(String.format("Invalid Token %s", token))
        );

        if(userAuthTokenEntity.getTokenUsed())
            throw new TokenAlreadyUsedExpection(String.format("Token %s is already used", token));

        if(userAuthTokenEntity.getTokenExpirationDate().isBefore(LocalDateTime.now()))
            throw new TokenAlreadyUsedExpection(String.format("The Token %s is already Expired", token));

        return true;
    }


    public UserAuthEntity getUserFromToken(String token){
        return userAuthTokenRepository.findByToken(token).orElseThrow(
                ()-> new TokenNotFoundException(String.format("Invalid Token %s", token))
        ).getUser();
    }

    @Override
    public void updateTokenUsed(String token) {
        UserAuthTokenEntity userAuthTokenEntity = userAuthTokenRepository.findByToken(token).orElseThrow(
                ()-> new TokenNotFoundException(String.format("Invalid Token %s", token))
        );
        userAuthTokenEntity.setTokenUsed(true);
        userAuthTokenRepository.save(userAuthTokenEntity);
    }

    @Scheduled(fixedRate = 1000*60*60)
    public void clearExpiredTokens(){
        List<UserAuthTokenEntity> userAuthTokenExpired = userAuthTokenRepository.findAllByTokenUsedFalseAndTokenExpirationDateBefore(LocalDateTime.now());

        if(!userAuthTokenExpired.isEmpty()){
            userAuthTokenRepository.deleteAll(userAuthTokenExpired);
        }
    }
}

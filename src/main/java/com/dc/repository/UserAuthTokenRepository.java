package com.dc.repository;

import com.dc.entity.UserAuthTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthTokenRepository extends JpaRepository<UserAuthTokenEntity, Long> {
    public Optional<UserAuthTokenEntity> findByToken(String token);
    public List<UserAuthTokenEntity> findAllByTokenRevokedTrueOrTokenExpirationDateBefore(LocalDateTime localDateTime);
    public List<UserAuthTokenEntity> findByUserEmail(String email);

}

package com.dc.repository;

import com.dc.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthEntity,Long> {
    public Boolean existsByEmail(String email);
    public Boolean existsByRoleID(Long id);
    public Optional<UserAuthEntity> findByEmail(String email);
}

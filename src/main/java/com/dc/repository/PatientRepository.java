package com.dc.repository;

import com.dc.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    Boolean existsByPhoneNumberAndDateOfBirth(String phoneNumber, Date dateOfBirth);
    Boolean existsByEmailAndDateOfBirth(String email, Date dateOfBirth);
}

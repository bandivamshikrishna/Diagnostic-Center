package com.dc.repository;

import com.dc.entity.PatientGenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientGenderRepository extends JpaRepository<PatientGenderEntity,Long> {
}

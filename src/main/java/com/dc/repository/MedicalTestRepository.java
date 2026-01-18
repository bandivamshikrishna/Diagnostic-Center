package com.dc.repository;

import com.dc.entity.MedicalTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalTestRepository extends JpaRepository<MedicalTestEntity, Long> {
    public Boolean existsByTestName(String testName);
    public Boolean existsByTestNameAndIdNot(String testName,Long id);
}

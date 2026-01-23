package com.dc.repository;

import com.dc.entity.VendorMedicalTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorMedicalTestRepository extends JpaRepository<VendorMedicalTestEntity, Long> {
}

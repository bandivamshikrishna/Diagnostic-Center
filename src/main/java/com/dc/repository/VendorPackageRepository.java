package com.dc.repository;

import com.dc.entity.VendorPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorPackageRepository extends JpaRepository<VendorPackageEntity,Long> { }

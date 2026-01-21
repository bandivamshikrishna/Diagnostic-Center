package com.dc.repository;

import com.dc.entity.PatientTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientTitleRepository extends JpaRepository<PatientTitleEntity,Long> {
}

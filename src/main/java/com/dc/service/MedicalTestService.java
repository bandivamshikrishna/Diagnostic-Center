package com.dc.service;

import com.dc.dto.MedicalTestCreateRequestDTO;
import com.dc.dto.MedicalTestResponseDTO;
import com.dc.dto.MedicalTestUpdateRequestDTO;

public interface MedicalTestService {

    public String createMedicalTest(MedicalTestCreateRequestDTO medicalTestCreateRequestDTO);
    public MedicalTestResponseDTO getMedicalTestByID(Long id);
    public String updateMedicalTestById(Long id, MedicalTestUpdateRequestDTO medicalTestUpdateRequestDTO);

}

package com.dc.serviceImpl;

import com.dc.dto.PatientCreateRequestDTO;
import com.dc.entity.PatientEntity;
import com.dc.entity.PatientGenderEntity;
import com.dc.entity.PatientTitleEntity;
import com.dc.entity.UserAuthEntity;
import com.dc.exception.PatientException;
import com.dc.mapper.PatientMapper;
import com.dc.repository.PatientGenderRepository;
import com.dc.repository.PatientRepository;
import com.dc.repository.PatientTitleRepository;
import com.dc.repository.UserAuthRepository;
import com.dc.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientTitleRepository patientTitleRepository;
    private final PatientGenderRepository patientGenderRepository;
    private final UserAuthRepository userAuthRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              PatientTitleRepository patientTitleRepository,
                              PatientGenderRepository patientGenderRepository,
                              UserAuthRepository userAuthRepository){
        this.patientRepository = patientRepository;
        this.patientTitleRepository = patientTitleRepository;
        this.patientGenderRepository = patientGenderRepository;
        this.userAuthRepository = userAuthRepository;
    }


    @Override
    public String createPatient(PatientCreateRequestDTO patientCreateRequestDTO) {
        PatientTitleEntity title = patientTitleRepository.findById(patientCreateRequestDTO.getTitleID()).orElseThrow(
                () -> new PatientException(String.format("Invalid Title ID : %d", patientCreateRequestDTO.getTitleID()),"titleID")
        );

        PatientGenderEntity gender = patientGenderRepository.findById(patientCreateRequestDTO.getGenderID()).orElseThrow(
                ()-> new PatientException(String.format("Invalid Gender ID : %d", patientCreateRequestDTO.getGenderID()),"genderID")
        );

        UserAuthEntity createdByUser = userAuthRepository.findById(patientCreateRequestDTO.getCreatedByUserID()).orElseThrow(
                ()-> new PatientException(String.format("Invalid Created By User ID : %d", patientCreateRequestDTO.getCreatedByUserID()),"createdByUserID")
        );

        PatientEntity patientEntity = PatientMapper.fromCreateDTOToEntity(patientCreateRequestDTO);
        patientEntity.setPatientTitle(title);
        patientEntity.setGender(gender);
        patientEntity.setCreatedBy(createdByUser);
        patientEntity.setCreatedDateTime(LocalDateTime.now());
        return String.format("Patient Created Successfully with ID : %d", patientRepository.save(patientEntity).getId());
    }
}

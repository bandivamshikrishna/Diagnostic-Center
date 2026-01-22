package com.dc.serviceImpl;

import com.dc.dto.PatientCreateRequestDTO;
import com.dc.entity.*;
import com.dc.exception.PatientException;
import com.dc.exception.VendorException;
import com.dc.mapper.PatientMapper;
import com.dc.repository.*;
import com.dc.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientTitleRepository patientTitleRepository;
    private final PatientGenderRepository patientGenderRepository;
    private final UserAuthRepository userAuthRepository;
    private final VendorRepository vendorRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              PatientTitleRepository patientTitleRepository,
                              PatientGenderRepository patientGenderRepository,
                              UserAuthRepository userAuthRepository,
                              VendorRepository vendorRepository){
        this.patientRepository = patientRepository;
        this.patientTitleRepository = patientTitleRepository;
        this.patientGenderRepository = patientGenderRepository;
        this.userAuthRepository = userAuthRepository;
        this.vendorRepository = vendorRepository;
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

        VendorEntity vendorEntity = vendorRepository.findById(patientCreateRequestDTO.getVendorID()).orElseThrow(
                () -> new VendorException("vendorID", String.format("Vendor not Found with ID : %d", patientCreateRequestDTO.getVendorID()))
        );

        PatientEntity patientEntity = PatientMapper.fromCreateDTOToEntity(patientCreateRequestDTO);
        patientEntity.setPatientTitle(title);
        patientEntity.setGender(gender);
        patientEntity.setVendorID(vendorEntity);
        patientEntity.setCreatedBy(createdByUser);
        patientEntity.setCreatedDateTime(LocalDateTime.now());
        return String.format("Patient Created Successfully with ID : %d", patientRepository.save(patientEntity).getId());
    }
}

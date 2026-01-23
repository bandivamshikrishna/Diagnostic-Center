package com.dc.serviceImpl;

import com.dc.dto.PatientCreateRequestDTO;
import com.dc.dto.PatientMedicalTestDTO;
import com.dc.dto.PatientPackageDTO;
import com.dc.entity.*;
import com.dc.exception.MedicalTestException;
import com.dc.exception.PatientException;
import com.dc.exception.VendorException;
import com.dc.mapper.PatientMapper;
import com.dc.repository.*;
import com.dc.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientTitleRepository patientTitleRepository;
    private final PatientGenderRepository patientGenderRepository;
    private final UserAuthRepository userAuthRepository;
    private final VendorRepository vendorRepository;
    private final VendorMedicalTestRepository vendorMedicalTestRepository;
    private final VendorPackageRepository vendorPackageRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              PatientTitleRepository patientTitleRepository,
                              PatientGenderRepository patientGenderRepository,
                              UserAuthRepository userAuthRepository,
                              VendorRepository vendorRepository,
                              VendorMedicalTestRepository vendorMedicalTestRepository,VendorPackageRepository vendorPackageRepository){
        this.patientRepository = patientRepository;
        this.patientTitleRepository = patientTitleRepository;
        this.patientGenderRepository = patientGenderRepository;
        this.userAuthRepository = userAuthRepository;
        this.vendorRepository = vendorRepository;
        this.vendorMedicalTestRepository = vendorMedicalTestRepository;
        this.vendorPackageRepository = vendorPackageRepository;
    }


    @Override
    public String createPatient(PatientCreateRequestDTO patientCreateRequestDTO) {

        if((patientCreateRequestDTO.getMedicalTests() == null || patientCreateRequestDTO.getMedicalTests().isEmpty())
            && (patientCreateRequestDTO.getPackages() == null || patientCreateRequestDTO.getPackages().isEmpty())){
            throw new PatientException("test and packages","Both Medical Tests and packages cannot be empty");
        }

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
        List<PatientMedicalTestEntity> patientMedicalTestEntities = new ArrayList<>();
        List<PatientMedicalTestPackageEntity> patientMedicalTestPackageEntities = new ArrayList<>();
        for (PatientMedicalTestDTO test : patientCreateRequestDTO.getMedicalTests()){

            VendorMedicalTestEntity vendorMedicalTestEntity = vendorMedicalTestRepository.findById(test.getId()).orElseThrow(
                    ()-> new VendorException("testID", "Test Not Found in Vendor System")
            );

            PatientMedicalTestEntity patientMedicalTestEntity = new PatientMedicalTestEntity();
            patientMedicalTestEntity.setPatient(patientEntity);
            patientMedicalTestEntity.setMedicalTest(vendorMedicalTestEntity);
            patientMedicalTestEntity.setTestDiscount(test.getDiscount());
            patientMedicalTestEntities.add(patientMedicalTestEntity);
        }

        for (PatientPackageDTO pkg : patientCreateRequestDTO.getPackages()){
            VendorPackageEntity vendorPackageEntity = vendorPackageRepository.findById(pkg.getId()).orElseThrow(
                    () -> new VendorException("package","Package Not Found in Vendor System")
            );

            PatientMedicalTestPackageEntity patientMedicalTestPackageEntity = new PatientMedicalTestPackageEntity();
            patientMedicalTestPackageEntity.setPackageDetails(vendorPackageEntity);
            patientMedicalTestPackageEntity.setPatient(patientEntity);
            patientMedicalTestPackageEntity.setPackageDiscount(pkg.getDiscount());

            patientMedicalTestPackageEntities.add(patientMedicalTestPackageEntity);
        }

        patientEntity.setMedicalTests(patientMedicalTestEntities);
        patientEntity.setPackages(patientMedicalTestPackageEntities);
        patientEntity.setPatientTitle(title);
        patientEntity.setGender(gender);
        patientEntity.setVendorID(vendorEntity);
        patientEntity.setCreatedBy(createdByUser);
        patientEntity.setCreatedDateTime(LocalDateTime.now());
        return String.format("Patient Created Successfully with ID : %d", patientRepository.save(patientEntity).getId());
    }
}

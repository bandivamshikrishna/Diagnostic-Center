package com.dc.serviceImpl;

import com.dc.dto.MedicalTestCreateRequestDTO;
import com.dc.dto.MedicalTestResponseDTO;
import com.dc.dto.MedicalTestUpdateRequestDTO;
import com.dc.entity.MedicalTestEntity;
import com.dc.entity.UserAuthEntity;
import com.dc.exception.MedicalTestException;
import com.dc.exception.UserException;
import com.dc.mapper.MedicalTestMapper;
import com.dc.repository.MedicalTestRepository;
import com.dc.repository.UserAuthRepository;
import com.dc.service.MedicalTestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MedicalTestServiceImpl implements MedicalTestService {

    private final MedicalTestRepository medicalTestRepository;
    private final UserAuthRepository userAuthRepository;

    public MedicalTestServiceImpl(MedicalTestRepository medicalTestRepository,UserAuthRepository userAuthRepository){
        this.medicalTestRepository = medicalTestRepository;
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public String createMedicalTest(MedicalTestCreateRequestDTO medicalTestCreateRequestDTO) {
        UserAuthEntity createdByUserID = userAuthRepository.findById(medicalTestCreateRequestDTO.getCreatedByUserID()).orElseThrow(
                () -> new UserException("createdByUserID",String.format("User Not Found with ID : %d", medicalTestCreateRequestDTO.getCreatedByUserID()))
        );
        if(medicalTestRepository.existsByTestName(medicalTestCreateRequestDTO.getTestName().toLowerCase().trim()))
            throw new MedicalTestException("testName",String.format("Medical Test Already Exists with Name : %s", medicalTestCreateRequestDTO.getTestName()));
        MedicalTestEntity medicalTestEntity = MedicalTestMapper.fromCreateDTOToEntity(medicalTestCreateRequestDTO);
        medicalTestEntity.setTestName(medicalTestEntity.getTestName().toLowerCase());
        medicalTestEntity.setActive(true);
        medicalTestEntity.setCreatedByUserID(createdByUserID);
        medicalTestEntity.setCreatedDate(LocalDateTime.now());
        return String.format("Test Created Successfully with ID : %d", medicalTestRepository.save(medicalTestEntity).getId());
    }

    @Override
    public MedicalTestResponseDTO getMedicalTestByID(Long id) {
        MedicalTestEntity medicalTestEntity = medicalTestRepository.findById(id).orElseThrow(
                () -> new MedicalTestException("id",String.format("Medical Test Not Found with ID : %s", id))
        );
        return MedicalTestMapper.fromEntityToDTO(medicalTestEntity);
    }

    @Override
    public String updateMedicalTestById(Long id, MedicalTestUpdateRequestDTO medicalTestUpdateRequestDTO) {
        UserAuthEntity lastModifiedByUserID = userAuthRepository.findById(medicalTestUpdateRequestDTO.getLastModifiedByUserID()).orElseThrow(
                () -> new UserException("lastModifiedByUserID",String.format("User Not Found with ID : %d", medicalTestUpdateRequestDTO.getLastModifiedByUserID()))
        );
        MedicalTestEntity medicalTestEntity = medicalTestRepository.findById(id).orElseThrow(
                ()-> new MedicalTestException("id",String.format("Medical Test Not Found with ID : %d", id))
        );

        if(medicalTestRepository.existsByTestNameAndIdNot(medicalTestUpdateRequestDTO.getTestName().toLowerCase().trim(),id))
            throw new MedicalTestException("testName",String.format("Medical Test Already Exists with name %s", medicalTestUpdateRequestDTO.getTestName()));

        MedicalTestMapper.fromUpdateDTOToEntity(medicalTestEntity,medicalTestUpdateRequestDTO);
        medicalTestEntity.setLastModifiedByUserID(lastModifiedByUserID);
        medicalTestEntity.setLastModifiedDate(LocalDateTime.now());
        medicalTestRepository.save(medicalTestEntity);
        return String.format("Medical Test Updated Successfully with ID : %d", id);
    }
}

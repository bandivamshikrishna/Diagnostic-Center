package com.dc.serviceImpl;


import com.dc.dto.VendorCreateRequestDTO;
import com.dc.dto.VendorResponseDTO;
import com.dc.dto.VendorUpdateRequestDTO;
import com.dc.entity.UserAuthEntity;
import com.dc.entity.VendorEntity;
import com.dc.exception.*;
import com.dc.mapper.VendorMapper;
import com.dc.repository.UserAuthRepository;
import com.dc.repository.VendorRepository;
import com.dc.service.VendorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final UserAuthRepository userAuthRepository;

    public VendorServiceImpl(VendorRepository vendorRepository,UserAuthRepository userAuthRepository){
        this.vendorRepository = vendorRepository;
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public String createVendor(VendorCreateRequestDTO vendorCreateRequestDTO) throws IOException {
        UserAuthEntity createdByUserID = userAuthRepository.findById(vendorCreateRequestDTO.getCreatedByUserID()).orElseThrow(
                () -> new UserException("createdByUserID",String.format("User Not Found with ID : %d", vendorCreateRequestDTO.getCreatedByUserID())));

        if(vendorRepository.existsByEmail(vendorCreateRequestDTO.getEmail().toLowerCase().trim()))
            throw new VendorException("email",String.format("Vendor Already Exists with Email ID : %s",vendorCreateRequestDTO.getEmail()));
        if(vendorRepository.existsByPhoneNumber(vendorCreateRequestDTO.getPhoneNumber()))
            throw new VendorException("phoneNumber","Phone Number Already Exists");
        VendorEntity vendorEntity = VendorMapper.fromCreateDTOToEntity(vendorCreateRequestDTO);
        vendorEntity.setCreatedByUserID(createdByUserID);
        vendorEntity.setCreatedDate( LocalDateTime.now());
        vendorEntity.setEmail(vendorEntity.getEmail().toLowerCase());
        vendorEntity.setActive(true);
        VendorEntity savedVendorEntity = vendorRepository.save(vendorEntity);

        //Email Notification
        return "Vendor Created Successfully with ID : "+savedVendorEntity.getId();
    }

    @Override
    public void getAllActiveVendors() {

    }

    @Override
    public VendorResponseDTO getVendorById(long id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).orElseThrow(
                () -> new VendorException("ID",String.format("Vendor with ID : %d is not Found", id)));
        return VendorMapper.fromEntityToDTO(vendorEntity);
    }

    @Override
    public String updateVendorById(long id, VendorUpdateRequestDTO vendorUpdateRequestDTO) {
        UserAuthEntity lastModifiedByUserID = userAuthRepository.findById(vendorUpdateRequestDTO.getLastModifiedByUserID()).orElseThrow(
                () -> new UserException("lastModifiedByUserID",String.format("User Not Found with ID : %d", vendorUpdateRequestDTO.getLastModifiedByUserID())));

        VendorEntity vendorEntity = vendorRepository.findById(id).orElseThrow(
                () -> new VendorException("ID",String.format("Vendor with ID : %d is not Found", id))
        );

        if(vendorRepository.existsByEmailAndIdNot(vendorUpdateRequestDTO.getEmail().toLowerCase().trim(),id))
            throw new VendorException("email",String.format("Vendor Already Exists with Email ID : %s", vendorUpdateRequestDTO.getEmail()));

        if(vendorRepository.existsByPhoneNumberAndIdNot(vendorUpdateRequestDTO.getPhoneNumber(), id))
            throw new VendorException("phoneNumber",String.format("Vendor Already Exists with Phone Number : %s", vendorUpdateRequestDTO.getPhoneNumber()));

        VendorMapper.fromUpdateDTOToEntity(vendorEntity,vendorUpdateRequestDTO);
        vendorEntity.setLastModifiedByUserID(lastModifiedByUserID);
        vendorEntity.setLastModifiedDate(LocalDateTime.now());
        vendorEntity.setEmail(vendorEntity.getEmail().toLowerCase());
        vendorRepository.save(vendorEntity);

        //Email Notification
        return String.format("Vendor Updated Successfully with ID : %d", id);
    }

    @Override
    public Long getVendorMaxNoOfUsers(Long id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).orElseThrow(
                ()-> new VendorException("ID",String.format("Vendor with ID : %d is not Found", id))
        );
        return vendorEntity.getMaxNoOfUsers();
    }
}


package com.dc.serviceImpl;


import com.dc.dto.VendorCreateRequestDTO;
import com.dc.dto.VendorResponseDTO;
import com.dc.dto.VendorUpdateRequestDTO;
import com.dc.entity.VendorEntity;
import com.dc.exception.PhoneNumberAlreadyExistsException;
import com.dc.exception.UserNotFoundException;
import com.dc.exception.VendorAlreadyExistsException;
import com.dc.exception.VendorNotFoundException;
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
        if(!userAuthRepository.existsById(vendorCreateRequestDTO.getCreatedByUserID()))
            throw new UserNotFoundException(String.format("User Not Found with ID : %d", vendorCreateRequestDTO.getCreatedByUserID()));

        if(vendorRepository.existsByEmail(vendorCreateRequestDTO.getEmail().toLowerCase().trim()))
            throw new VendorAlreadyExistsException(String.format("Vendor Already Exists with Email ID : %s",vendorCreateRequestDTO.getEmail()));
        if(vendorRepository.existsByPhoneNumber(vendorCreateRequestDTO.getPhoneNumber()))
            throw new PhoneNumberAlreadyExistsException("Phone Number Already Exists");
        VendorEntity vendorEntity = VendorMapper.fromCreateDTOToEntity(vendorCreateRequestDTO);
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
                () -> new VendorNotFoundException(String.format("Vendor with ID : %d is not Found", id)));
        return VendorMapper.fromEntityToDTO(vendorEntity);
    }

    @Override
    public String updateVendorById(long id, VendorUpdateRequestDTO vendorUpdateRequestDTO) {
        if(!userAuthRepository.existsById(vendorUpdateRequestDTO.getLastModifiedByUserID()))
            throw new UserNotFoundException(String.format("User Not Found with ID : %d", vendorUpdateRequestDTO.getLastModifiedByUserID()));

        VendorEntity vendorEntity = vendorRepository.findById(id).orElseThrow(
                () -> new VendorNotFoundException(String.format("Vendor with ID : %d is not Found", id))
        );

        if(vendorRepository.existsByEmailAndIdNot(vendorUpdateRequestDTO.getEmail().toLowerCase().trim(),id))
            throw new VendorAlreadyExistsException(String.format("Vendor Already Exists with Email ID : %s", vendorUpdateRequestDTO.getEmail()));

        if(vendorRepository.existsByPhoneNumberAndIdNot(vendorUpdateRequestDTO.getPhoneNumber(), id))
            throw new VendorAlreadyExistsException(String.format("Vendor Already Exists with Phone Number : %s", vendorUpdateRequestDTO.getPhoneNumber()));

        VendorMapper.fromUpdateDTOToEntity(vendorEntity,vendorUpdateRequestDTO);
        vendorEntity.setLastModifiedDate(LocalDateTime.now());
        vendorEntity.setEmail(vendorEntity.getEmail().toLowerCase());
        vendorRepository.save(vendorEntity);

        //Email Notification
        return String.format("Vendor Updated Successfully with ID : %d", id);
    }

    @Override
    public Long getVendorMaxNoOfUsers(Long id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).orElseThrow(
                ()-> new VendorNotFoundException(String.format("Vendor with ID : %d is not Found", id))
        );
        return vendorEntity.getMaxNoOfUsers();
    }
}


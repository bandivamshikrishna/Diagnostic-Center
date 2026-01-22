package com.dc.mapper;


import com.dc.dto.VendorCreateRequestDTO;
import com.dc.dto.VendorResponseDTO;
import com.dc.dto.VendorUpdateRequestDTO;
import com.dc.entity.VendorEntity;

import java.io.IOException;
import java.time.LocalDateTime;

public class VendorMapper {
    public static VendorEntity fromCreateDTOToEntity(VendorCreateRequestDTO vendorCreateRequestDTO) throws IOException {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setName(vendorCreateRequestDTO.getName());
        vendorEntity.setEmail(vendorCreateRequestDTO.getEmail());
        vendorEntity.setAddress(vendorCreateRequestDTO.getAddress());
        vendorEntity.setPhoneNumber(vendorCreateRequestDTO.getPhoneNumber());
        vendorEntity.setLogo(vendorCreateRequestDTO.getLogo().getBytes());
        vendorEntity.setActivationEndDate(LocalDateTime.parse(vendorCreateRequestDTO.getActivationEndDate()));
        vendorEntity.setMaxNoOfUsers(vendorCreateRequestDTO.getMaxNoOfUsers());
        return vendorEntity;
    }


    public static VendorResponseDTO fromEntityToDTO(VendorEntity vendorEntity){
        VendorResponseDTO vendorResponseDTO = new VendorResponseDTO();
        vendorResponseDTO.setId(vendorEntity.getId());
        vendorResponseDTO.setName(vendorEntity.getName());
        vendorResponseDTO.setEmail(vendorEntity.getEmail());
        vendorResponseDTO.setPhoneNumber(vendorEntity.getPhoneNumber());
        vendorResponseDTO.setAddress(vendorEntity.getAddress());
        vendorResponseDTO.setCreatedByUserID(vendorEntity.getCreatedByUserID().getId());
        vendorResponseDTO.setCreatedDate(vendorEntity.getCreatedDate().toString());
        vendorResponseDTO.setActive(vendorEntity.isActive());
        vendorResponseDTO.setActivationEndDate(vendorEntity.getActivationEndDate().toString());
        vendorResponseDTO.setMaxNoOfUsers(vendorEntity.getMaxNoOfUsers());
        return vendorResponseDTO;
    }

    public static void fromUpdateDTOToEntity(VendorEntity vendorEntity, VendorUpdateRequestDTO vendorUpdateRequestDTO){
        vendorEntity.setName(vendorUpdateRequestDTO.getName());
        vendorEntity.setEmail(vendorUpdateRequestDTO.getEmail());
        vendorEntity.setAddress(vendorUpdateRequestDTO.getAddress());
        vendorEntity.setActive(vendorUpdateRequestDTO.isActive());
        vendorEntity.setActivationEndDate(LocalDateTime.parse(vendorUpdateRequestDTO.getActivationEndDate()));
        vendorEntity.setMaxNoOfUsers(vendorUpdateRequestDTO.getMaxNoOfUsers());
    }
}


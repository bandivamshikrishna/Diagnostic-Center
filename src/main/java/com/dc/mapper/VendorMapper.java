package com.dc.mapper;


import com.dc.dto.VendorCreatePackageRequestDTO;
import com.dc.dto.VendorCreateRequestDTO;
import com.dc.dto.VendorResponseDTO;
import com.dc.dto.VendorUpdateRequestDTO;
import com.dc.entity.VendorBranchEntity;
import com.dc.entity.VendorEntity;
import com.dc.entity.VendorPackageEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VendorMapper {
    public static VendorEntity fromCreateDTOToEntity(VendorCreateRequestDTO vendorCreateRequestDTO) throws IOException {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setName(vendorCreateRequestDTO.getName());
        vendorEntity.setEmail(vendorCreateRequestDTO.getEmail());
        vendorEntity.setAddress(vendorCreateRequestDTO.getAddress());
        vendorEntity.setPhoneNumber(vendorCreateRequestDTO.getPhoneNumber());
        vendorEntity.setActivationEndDate(LocalDateTime.parse(vendorCreateRequestDTO.getActivationEndDate()));
        vendorEntity.setMaxNoOfUsers(vendorCreateRequestDTO.getMaxNoOfUsers());

        List<VendorBranchEntity> vendorBranchEntities = vendorCreateRequestDTO.getBranches().stream().map(
                (branch) -> {
                    VendorBranchEntity vendorBranchEntity = new VendorBranchEntity();
                    vendorBranchEntity.setVendor(vendorEntity);
                    vendorBranchEntity.setBranchCode(branch.getBranchCode());
                    vendorBranchEntity.setBranchName(branch.getBranchName());
                    vendorBranchEntity.setBranchAddress(branch.getBranchAddress());
                    return vendorBranchEntity;
                }
        ).toList();
        vendorEntity.setBranches(vendorBranchEntities);
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


    public static VendorPackageEntity fromCreateDTOToEntity(VendorCreatePackageRequestDTO vendorCreatePackageRequestDTO){
        VendorPackageEntity vendorPackageEntity = new VendorPackageEntity();
        vendorPackageEntity.setPackageName(vendorCreatePackageRequestDTO.getName());
        vendorPackageEntity.setPackagePrice(vendorCreatePackageRequestDTO.getPackagePrice());
        return vendorPackageEntity;
    }
}


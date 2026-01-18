package com.dc.mapper;

import com.dc.dto.UserCreateRequestDTO;
import com.dc.entity.UserAuthEntity;

public class UserAuthMapper {

    public static UserAuthEntity fromCreateDTOToEntity(UserCreateRequestDTO userCreateRequestDTO){
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setEmail(userCreateRequestDTO.getEmail());
        userAuthEntity.setRoleID(userCreateRequestDTO.getRoleID());
        userAuthEntity.setVendorID(userCreateRequestDTO.getVendorID());
        userAuthEntity.setCreatedByUserID(userCreateRequestDTO.getCreatedByUserID());
        return userAuthEntity;
    }
}

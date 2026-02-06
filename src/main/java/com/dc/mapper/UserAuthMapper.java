package com.dc.mapper;

import com.dc.dto.UserCreateRequestDTO;
import com.dc.dto.UserResponseDTO;
import com.dc.entity.UserAuthEntity;
import com.dc.enums.RoleEnum;

public class UserAuthMapper {

    public static UserAuthEntity fromCreateDTOToEntity(UserCreateRequestDTO userCreateRequestDTO){
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setEmail(userCreateRequestDTO.getEmail());
        userAuthEntity.setRoleID(userCreateRequestDTO.getRoleID());
        return userAuthEntity;
    }

    public static UserResponseDTO fromEntityToDTO(UserAuthEntity userAuthEntity){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(userAuthEntity.getEmail());
        userResponseDTO.setRole(RoleEnum.getRole(userAuthEntity.getRoleID()));
        return userResponseDTO;
    }
}

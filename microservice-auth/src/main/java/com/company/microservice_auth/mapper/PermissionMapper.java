package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.permission.PermissionCreateRequestDTO;
import com.company.microservice_auth.dto.permission.PermissionDTO;
import com.company.microservice_auth.dto.permission.PermissionResponseDTO;
import com.company.microservice_auth.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PermissionMapper {

    PermissionMapper instance = Mappers.getMapper(PermissionMapper.class);

    Permission permissionCreateRequestDTOToPermission(PermissionCreateRequestDTO requestDTO);

    Permission permissionDTOToPermission(PermissionDTO permissionDTO);

    PermissionResponseDTO permissionToPermissionResponseDTO(Permission permission);

    List<PermissionResponseDTO> listPermissionToListPermissionResponseDTO(List<Permission> list);

    
}

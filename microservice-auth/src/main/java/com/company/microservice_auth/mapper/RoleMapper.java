package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.role.RoleCreateRequestDTO;
import com.company.microservice_auth.dto.role.RoleDTO;
import com.company.microservice_auth.dto.role.RoleResponseDTO;
import com.company.microservice_auth.dto.role.RoleUpdateRequestDTO;
import com.company.microservice_auth.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper instance = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "statusDTO", target = "status")
    Role roleCreateRequestDTOToRole(RoleCreateRequestDTO requestDTO);

    @Mapping(source = "statusDTO", target = "status")
    Role roleDTOToRole(RoleUpdateRequestDTO roleDTO);

    @Mapping(source = "status", target = "statusDTO")
    RoleResponseDTO roleToRoleResponseDTO(Role role);

    @Mapping(source = "status", target = "statusDTO")
    List<RoleResponseDTO> listRoleToListRoleResponseDTO(List<Role> list);


}

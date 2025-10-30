package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.user.UserRoleDTO;
import com.company.microservice_auth.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface UserRoleMapper {

    UserRoleMapper instancia = Mappers.getMapper(UserRoleMapper.class);


    Set<UserRole> userRoleDTOToUserRole(Set<UserRoleDTO> userRoleDTO);

    Set<UserRoleDTO> userRoleToUserRoleDTO(Set<UserRole> userRole);

}

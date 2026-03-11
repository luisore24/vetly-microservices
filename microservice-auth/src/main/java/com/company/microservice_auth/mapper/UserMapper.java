package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.user.UserCreateRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;
import com.company.microservice_auth.entity.Status;
import com.company.microservice_auth.entity.User;
import com.company.microservice_auth.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserMapper {

    //UserMapper instance = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "statusDTO", target = "status")
    @Mapping(source = "rolesDTO", target = "roles", ignore = true)
    User userCreateRequestDTOToUser(UserCreateRequestDTO requestDTO);


    @Mapping(target = "statusDTO", source = "status", qualifiedByName = "statusToDescription")
    @Mapping(target = "rolesDTO", source = "roles", qualifiedByName = "rolesToNames")
    UserResponseDTO userToUserResponseDTO(User user);


    @Mapping(target = "statusDTO", source = "status", qualifiedByName = "statusToDescription")
    @Mapping(target = "rolesDTO", source = "roles", qualifiedByName = "rolesToNames")
    List<UserResponseDTO> listUserToListUserResponseDTO(List<User> list);


    @Named("rolesToNames")
    default Set<String> mapRoles(Set<UserRole> userRoles){

        if(userRoles == null || userRoles.isEmpty()) return Collections.emptySet();

        return userRoles.stream()
                .map(role -> role.getRole().getDescription())
                .collect(Collectors.toSet());
    }

    @Named("statusToDescription")
    default String mapStatus(Status status){

        if(status == null ) return "";

        return status.getDescription();
    }

}


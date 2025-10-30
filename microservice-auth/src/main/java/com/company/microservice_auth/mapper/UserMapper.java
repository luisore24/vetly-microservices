package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.role.RoleSumary;
import com.company.microservice_auth.dto.user.UserDTO;
import com.company.microservice_auth.dto.user.UserCreateRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;
import com.company.microservice_auth.entity.User;
import com.company.microservice_auth.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "statusDTO", target = "status")
    @Mapping(source = "rolesDTO", target = "roles", ignore = true)
    User userCreateRequestDTOToUser(UserCreateRequestDTO requestDTO);


    @Mapping(source = "status", target = "statusDTO")
    @Mapping(target = "rolesDTO", expression = "java(mapRoles(user.getRoles()))")
    UserResponseDTO userToUserResponseDTO(User user);

    @Mapping(source = "status", target = "statusDTO")
    @Mapping(target = "rolesDTO", expression = "java(mapRoles(user.getRoles()))")
    List<UserResponseDTO> listUserToListUserResponseDTO(List<User> list);


    default Set<RoleSumary> mapRoles(Set<UserRole> userRoles){
        return userRoles.stream()
                .map(role -> new RoleSumary(role.getRole().getId(), role.getRole().getDescription(), role.getRole().getComment()))
                .collect(Collectors.toSet());
    }

}


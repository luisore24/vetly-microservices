package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.user.UserRequestDTO;
import com.company.microservice_auth.entity.User;
import org.mapstruct.factory.Mappers;

public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    User userRequestDTOToUser(UserRequestDTO requestDTO);

}

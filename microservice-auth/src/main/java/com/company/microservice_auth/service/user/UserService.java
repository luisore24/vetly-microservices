package com.company.microservice_auth.service.user;

import com.company.microservice_auth.dto.user.UserCreateRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;
import com.company.microservice_auth.dto.user.UserUpdateRequestDTO;
import com.company.microservice_auth.service.CommonService;

public interface UserService extends CommonService<UserCreateRequestDTO, UserUpdateRequestDTO,UserResponseDTO> {



}

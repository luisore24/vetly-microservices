package com.company.microservice_auth.service.user;

import com.company.microservice_auth.dto.user.UserRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;

public interface UserService {

    UserResponseDTO register(UserRequestDTO request);

}

package com.company.microservice_auth.service.auth;

import com.company.microservice_auth.dto.auth.LoginRequest;
import com.company.microservice_auth.dto.auth.LoginResponse;

public interface LoginService {

    LoginResponse loginAuthenticate(LoginRequest request);

}

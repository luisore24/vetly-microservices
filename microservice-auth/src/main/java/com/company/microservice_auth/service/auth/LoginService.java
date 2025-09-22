package com.company.microservice_auth.service.auth;

import com.company.microservice_auth.dto.auth.LoginRequest;
import com.company.microservice_auth.dto.auth.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {

    LoginResponse loginAuthenticate(LoginRequest request, HttpServletRequest httpServletRequest);

}

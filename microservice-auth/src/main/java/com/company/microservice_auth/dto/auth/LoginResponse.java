package com.company.microservice_auth.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private String token;
    private String statusCode;

}

package com.company.microservice_auth.service.auth;

import com.company.microservice_auth.dto.auth.AuditLoginRequestDTO;
import com.company.microservice_auth.dto.auth.AuditLoginResponseDTO;

import java.util.Optional;

public interface AuditLoginService {

    void register(AuditLoginRequestDTO request);

}

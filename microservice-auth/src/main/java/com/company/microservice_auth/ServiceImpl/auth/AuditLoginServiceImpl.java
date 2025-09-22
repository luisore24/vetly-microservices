package com.company.microservice_auth.ServiceImpl.auth;

import com.company.microservice_auth.dto.auth.AuditLoginRequestDTO;
import com.company.microservice_auth.dto.auth.AuditLoginResponseDTO;
import com.company.microservice_auth.entity.AuditLogin;
import com.company.microservice_auth.mapper.AuditLoginMapper;
import com.company.microservice_auth.repository.auth.AuditLoginRepository;
import com.company.microservice_auth.service.auth.AuditLoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuditLoginServiceImpl implements AuditLoginService {

    private final AuditLoginRepository auditLoginRepository;


    @Override
    public void register(AuditLoginRequestDTO request) {

        AuditLogin auditLoginRequest = AuditLoginMapper.instance.auditLoginRequestDToToAuditLogin(request);
        AuditLogin auditLoginRegister = auditLoginRepository.save(auditLoginRequest);

    }



}

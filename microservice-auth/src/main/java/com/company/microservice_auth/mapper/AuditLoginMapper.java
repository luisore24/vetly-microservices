package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.auth.AuditLoginRequestDTO;
import com.company.microservice_auth.dto.auth.AuditLoginResponseDTO;
import com.company.microservice_auth.entity.AuditLogin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuditLoginMapper {


    AuditLoginMapper instance = Mappers.getMapper(AuditLoginMapper.class);

    AuditLogin auditLoginRequestDToToAuditLogin(AuditLoginRequestDTO requestDTO);

    AuditLoginResponseDTO auditLoginToAuditLoginResponseDTO(AuditLogin entity);


}

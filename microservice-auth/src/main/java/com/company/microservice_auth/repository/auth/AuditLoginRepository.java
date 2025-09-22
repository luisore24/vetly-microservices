package com.company.microservice_auth.repository.auth;

import com.company.microservice_auth.entity.AuditLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditLoginRepository extends JpaRepository<AuditLogin, UUID> {
}

package com.company.microservice_auth.dto.auth;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class AuditLoginRequestDTO {

    private String username;

    private String userAgent;

    private String ipAddress;

    private LocalDateTime createdAt;

    private String message;

    private Boolean isSuccessful;


}

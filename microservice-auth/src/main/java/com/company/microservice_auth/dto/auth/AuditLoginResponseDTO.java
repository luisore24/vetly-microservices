package com.company.microservice_auth.dto.auth;

import com.company.microservice_auth.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditLoginResponseDTO {

    private UUID id;

    private String username;

    private String userAgent;

    private String ipAddress;

    private LocalDateTime createdAt;

    private String message;

    private Boolean isSuccessful;

}

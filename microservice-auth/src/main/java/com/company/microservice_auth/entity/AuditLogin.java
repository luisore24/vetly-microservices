package com.company.microservice_auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_login")
@Data
@Builder
public class AuditLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_audit_login_user"))
    private User user;

    @Column(name = "username")
    private String username;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_successful")
    private boolean isSuccessful;

}

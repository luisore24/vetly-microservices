package com.company.microservice_auth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> roles;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @ManyToOne()
    @JoinColumn(name = "status_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_status"))
    private Status status;

    @Column(name = "is_enable", nullable = false)
    private boolean isEnabled;

    @Column(name = "account_no_expired", nullable = false)
    private boolean accountNoExpired;

    @Column(name = "account_no_locked", nullable = false)
    private boolean accountNoLocked;

    @Column(name = "credential_no_expired", nullable = false)
    private boolean credentialNoExpired;

    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;

    @Column(name = "last_login_ip", nullable = false)
    private String lastLoginIp;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_by", nullable = false)
    private String createBy;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "update_by", nullable = false)
    private String updateBy;

}

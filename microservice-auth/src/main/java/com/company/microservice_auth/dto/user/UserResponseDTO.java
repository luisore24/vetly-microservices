package com.company.microservice_auth.dto.user;

import com.company.microservice_auth.entity.Status;
import com.company.microservice_auth.entity.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    
    private String username;

    private Set<UserRole> roles;

    private String name;

    private String lastname;

    private String email;
    
    private String phone;

    private String address;

    private Status status;
    
//    private Boolean isEnabled;
//
//    private Boolean accountNoExpired;
//
//    private Boolean accountNoLocked;
//
//    private Boolean credentialNoExpired;
//
//    private LocalDateTime createdAt;
//
//    private String createdBy;
//
//    private LocalDateTime updatedAt;
//
//    private String updatedBy;
//
//    private Boolean isDeleted;
    
}

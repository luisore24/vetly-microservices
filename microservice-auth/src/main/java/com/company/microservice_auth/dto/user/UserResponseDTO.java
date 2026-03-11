package com.company.microservice_auth.dto.user;

import com.company.microservice_auth.dto.role.RoleSumary;
import com.company.microservice_auth.dto.status.StatusDTO;
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

    //private Set<RoleSumary> rolesDTO;
    private Set<String> rolesDTO;

    private String name;

    private String lastname;

    private String email;
    
    private String phone;

    private String address;

    //private StatusDTO statusDTO;
    private String statusDTO;
    
    private Boolean isEnabled;

    private Boolean accountNoExpired;

    private Boolean accountNoLocked;

    private Boolean credentialNoExpired;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
    
}

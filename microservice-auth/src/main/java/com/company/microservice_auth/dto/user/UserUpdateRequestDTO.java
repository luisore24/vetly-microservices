package com.company.microservice_auth.dto.user;

import com.company.microservice_auth.dto.status.StatusDTO;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {

    private Long id;
    
    private String username;

    private Set<UserRoleDTO> rolesDTO;

    private String name;

    private String lastname;

    private String email;
    
    private String phone;

    private String address;

    private StatusDTO status;
    
    private Boolean isEnabled;

    private Boolean accountNoExpired;

    private Boolean accountNoLocked;

    private Boolean credentialNoExpired;

    
}

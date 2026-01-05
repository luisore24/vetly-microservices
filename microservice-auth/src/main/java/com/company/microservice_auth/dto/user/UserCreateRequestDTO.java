package com.company.microservice_auth.dto.user;

import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCreateRequestDTO {

    private String username;

    @ToString.Exclude
    private String password;

    private Set<Long> rolesDTO;

    private String name;

    private String lastname;

    private String email;

    private String phone;

    private String address;

    private StatusDTO statusDTO;

    private Boolean isEnabled;
    
}

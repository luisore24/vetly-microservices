package com.company.microservice_auth.dto.permission;

import com.company.microservice_auth.dto.role.RolePermissionDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PermissionCreateRequestDTO {


    private String description;

    private String comment;

    private StatusDTO statusDTO;


}

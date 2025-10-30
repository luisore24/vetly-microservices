package com.company.microservice_auth.dto.permission;

import com.company.microservice_auth.dto.role.RolePermissionDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PermissionDTO {

    private Long id;

    private Set<RolePermissionDTO> rolePermissionsDTO;

    private String description;

    private String comment;

    private StatusDTO statusDTO;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}

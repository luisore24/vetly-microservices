package com.company.microservice_auth.dto.role;

import com.company.microservice_auth.dto.status.StatusDTO;
import com.company.microservice_auth.dto.user.UserRoleDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleDTO {

    private Long id;

    //private Set<UserRoleDTO> userRolesDTO;

    private Set<RolePermissionDTO> rolePermissionsDTO;

    private Set<RoleMenuDTO> roleMenusDTO;

    private String description;

    private String comment;

    private StatusDTO statusDTO;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}

package com.company.microservice_auth.dto.role;

import com.company.microservice_auth.dto.permission.PermissionDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RolePermissionDTO {

    private Long id;

    //private RoleDTO roleDTO;

    private PermissionDTO permissionDTO;

    private StatusDTO statusDTO;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}

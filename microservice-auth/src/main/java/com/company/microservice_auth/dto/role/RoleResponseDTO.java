package com.company.microservice_auth.dto.role;

import com.company.microservice_auth.dto.status.StatusDTO;
import com.company.microservice_auth.dto.user.UserRoleDTO;
import com.company.microservice_auth.entity.RoleMenu;
import com.company.microservice_auth.entity.RolePermission;
import com.company.microservice_auth.entity.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleResponseDTO {

    private Long id;

    private String description;

    private String comment;

    private StatusDTO statusDTO;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}

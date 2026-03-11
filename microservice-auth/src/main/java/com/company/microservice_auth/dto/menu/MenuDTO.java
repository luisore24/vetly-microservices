package com.company.microservice_auth.dto.menu;

import com.company.microservice_auth.dto.role.RoleMenuDTO;
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
public class MenuDTO {

    private Long id;

    private Set<RoleMenuDTO> roleMenusDTO;

    private String description;

    private String icon;

    private String route;

    private StatusDTO statusDTO;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}

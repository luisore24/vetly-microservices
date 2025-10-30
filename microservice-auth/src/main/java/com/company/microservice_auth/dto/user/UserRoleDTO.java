package com.company.microservice_auth.dto.user;

import com.company.microservice_auth.dto.role.RoleDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRoleDTO {

    private Long id;

    //private UserDTO userDTO;

    private RoleDTO roleDTO;

    private StatusDTO statusDTO;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}

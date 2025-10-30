package com.company.microservice_auth.dto.role;

import com.company.microservice_auth.dto.menu.MenuDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleMenuCreateDTO {

    private RoleDTO roleDTO;

    private MenuDTO menuDTO;

    private StatusDTO statusDTO;


}

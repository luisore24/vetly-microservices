package com.company.microservice_auth.dto.role;

import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleCreateRequestDTO {

    private String description;

    private String comment;

    private StatusDTO statusDTO;

}

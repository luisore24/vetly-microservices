package com.company.microservice_auth.dto.role;

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
public class RoleUpdateRequestDTO {

    private Long id;

    private String description;

    private String comment;

    private StatusDTO statusDTO;


}

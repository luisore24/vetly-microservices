package com.company.microservice_auth.dto.role;

import com.company.microservice_auth.dto.status.StatusDTO;
import lombok.*;
import java.time.LocalDateTime;

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

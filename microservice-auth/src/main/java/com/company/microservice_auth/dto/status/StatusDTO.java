package com.company.microservice_auth.dto.status;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StatusDTO {

    private Long id;

    private String description;

    private String comment;

    private Boolean isAllowed;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;


}

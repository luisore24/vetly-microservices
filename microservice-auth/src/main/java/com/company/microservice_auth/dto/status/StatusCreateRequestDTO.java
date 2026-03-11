package com.company.microservice_auth.dto.status;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StatusCreateRequestDTO {

    private String description;

    private String comment;

    private Boolean isAllowed;

    private LocalDateTime createdAt;

    private String createdBy;



}

package com.company.microservice_catalog.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditData {

    private String create_by;
    private LocalDateTime create_at;
    private String update_by;
    private LocalDateTime update_at;
}

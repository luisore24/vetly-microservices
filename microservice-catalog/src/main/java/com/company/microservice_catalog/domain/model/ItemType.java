package com.company.microservice_catalog.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemType {

    private Long id;
    private String description;
    private Boolean status;
    private AuditData audit;

}

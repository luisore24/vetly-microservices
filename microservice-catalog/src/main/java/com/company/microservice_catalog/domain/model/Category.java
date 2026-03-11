package com.company.microservice_catalog.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    private Long id;
    private String name;
    private String description;
    private ItemType type;
    private Boolean status;
    private AuditData audit;

}

package com.company.microservice_catalog.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CatalogItem {

    private Long id;
    private String name;
    private String description;
    private ItemType type;
    private Double price;
    private Long location;
    private Boolean status;
    private Boolean isDeleted;
    private AuditData audit;

}

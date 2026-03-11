package com.company.microservice_catalog.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OfferedService extends CatalogItem{

    private Category category;
    private Integer estimatedDurationMin;
    private String considerations;
}

package com.company.microservice_catalog.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product extends CatalogItem{

    private String brand;
    private Category category;
    private String usageInstruction;
    private Integer stock;

}

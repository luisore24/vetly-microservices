package com.company.microservice_catalog.domain.model;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogItemSearch {

    private Long id;
    private String name;
    private ItemType type;
    private Double price;
    private Long location;
    private Boolean status;

}

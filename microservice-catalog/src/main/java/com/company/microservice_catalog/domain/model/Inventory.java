package com.company.microservice_catalog.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory{

    private Long id;
    private Long catalogoItemId;
    private Long locationId;
    private BigDecimal price;
    private Long stock;
    private Boolean isAvailable;
    private Boolean isDeleted;



}

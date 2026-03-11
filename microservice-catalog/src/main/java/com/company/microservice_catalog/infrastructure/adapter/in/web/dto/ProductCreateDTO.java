package com.company.microservice_catalog.infrastructure.adapter.in.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {

    private String name;
    private String description;
    private Long type;
    private Double price;
    private String brand;
    private Long category;
    private String usageInstruction;
    private Integer stock;
    private Long location;

}

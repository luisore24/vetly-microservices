package com.company.microservice_catalog.infrastructure.adapter.out.persistence.service;


import com.company.microservice_catalog.infrastructure.adapter.out.persistence.category.CategoryEntity;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type.ItemTypeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemTypeEntity type;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    private Integer estimatedDurationMin;
    private String considerations;
    private Long location;
    private Boolean status;
    private Boolean isDeleted;
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;



}

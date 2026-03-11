package com.company.microservice_catalog.infrastructure.adapter.out.persistence.catalog;

import com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type.ItemTypeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "catalog_items")
public class CatalogItemSearchEntity {

    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemTypeEntity type;
    private Double price;
    private Long location;
    private Boolean status;

    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;


}

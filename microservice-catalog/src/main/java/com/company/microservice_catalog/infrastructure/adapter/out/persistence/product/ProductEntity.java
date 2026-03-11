package com.company.microservice_catalog.infrastructure.adapter.out.persistence.product;

import com.company.microservice_catalog.infrastructure.adapter.out.persistence.category.CategoryEntity;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type.ItemTypeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemTypeEntity type;
    private Double price;
    private String brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    private String usageInstruction;
    private Integer stock;
    private Long location;

    @ColumnDefault("true")
    private Boolean status;

    @ColumnDefault("false")
    private Boolean isDeleted;
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;


}

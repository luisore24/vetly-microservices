package com.company.microservice_catalog.infrastructure.adapter.out.persistence.category;

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
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_type_id" )
    private ItemTypeEntity type;
    private Boolean status;
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;


}

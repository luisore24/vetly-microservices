package com.company.microservice_catalog.infrastructure.adapter.out.persistence.catalog;

import com.company.microservice_catalog.application.port.out.CatalogItemSearchRepositoryPort;
import com.company.microservice_catalog.domain.model.ItemType;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type.ItemTypeEntity;
import com.company.microservice_catalog.domain.model.CatalogItemSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CatalogItemSearchPersistenceAdapter implements CatalogItemSearchRepositoryPort {


    private final JpaCatalogItemSearchRepository jpaCatalogItemSearchRepository;

    @Override
    public List<CatalogItemSearch> searchByName(String name) {
        return jpaCatalogItemSearchRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::catalogItemSearchToDomain)
                .toList();

    }

private CatalogItemSearch catalogItemSearchToDomain(CatalogItemSearchEntity entity){
        return CatalogItemSearch.builder()
                 .id(entity.getId())
                 .name(entity.getName())
                .type(itemTypeEntityToDomain(entity.getType()))
                .price(entity.getPrice())
                .location(entity.getLocation())
                .status(entity.getStatus())
                .build();

}

private ItemType itemTypeEntityToDomain(ItemTypeEntity type){
    return ItemType.builder()
            .id(type.getId())
            .description(type.getDescription())
            .status(type.getStatus())
            .build();
}


}

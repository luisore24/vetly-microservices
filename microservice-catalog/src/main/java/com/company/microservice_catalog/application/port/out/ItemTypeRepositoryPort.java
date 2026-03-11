package com.company.microservice_catalog.application.port.out;

import com.company.microservice_catalog.domain.model.ItemType;

import java.util.List;
import java.util.Optional;

public interface ItemTypeRepositoryPort {

    ItemType saveItemType(ItemType itemType);

    ItemType updateItemType(Long id, ItemType itemType);

    Optional<ItemType> findItemType(Long id);

    List<ItemType> findAllItemsType();

    void deleteItemType(Long id);
    
}

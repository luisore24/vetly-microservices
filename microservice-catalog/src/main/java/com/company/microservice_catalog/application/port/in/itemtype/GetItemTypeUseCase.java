package com.company.microservice_catalog.application.port.in.itemtype;

import com.company.microservice_catalog.domain.model.ItemType;
import com.company.microservice_catalog.domain.model.OfferedService;

import java.util.List;
import java.util.Optional;

public interface GetItemTypeUseCase {

    Optional<ItemType> getItemType(Long id);

    List<ItemType> getAllItemsType();

}

package com.company.microservice_catalog.application.port.in.itemtype;

import com.company.microservice_catalog.domain.model.ItemType;

public interface UpdateItemTypeUseCase {

    ItemType execute(Long id, ItemType itemType);

}

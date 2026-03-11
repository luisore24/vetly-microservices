package com.company.microservice_catalog.application.port.in.itemtype;

import com.company.microservice_catalog.domain.model.ItemType;

public interface CreateItemTypeUseCase {

    ItemType execute(ItemType itemType);

}

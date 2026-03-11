package com.company.microservice_catalog.application.usecase.itemtype;

import com.company.microservice_catalog.application.port.in.itemtype.CreateItemTypeUseCase;
import com.company.microservice_catalog.application.port.out.ItemTypeRepositoryPort;
import com.company.microservice_catalog.domain.model.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CreateItemTypeUseCaseService implements CreateItemTypeUseCase {

    private final ItemTypeRepositoryPort itemTypeRepositoryPort;

    @Override
    @Transactional
    public ItemType execute(ItemType itemType) {
        return itemTypeRepositoryPort.saveItemType(itemType);
    }
}

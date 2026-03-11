package com.company.microservice_catalog.application.usecase.itemtype;

import com.company.microservice_catalog.application.port.in.itemtype.UpdateItemTypeUseCase;
import com.company.microservice_catalog.application.port.out.ItemTypeRepositoryPort;
import com.company.microservice_catalog.domain.model.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateItemTypeUseCaseService implements UpdateItemTypeUseCase {

    private final ItemTypeRepositoryPort itemTypeRepositoryPort;


    @Override
    @Transactional
    public ItemType execute(Long id, ItemType itemType) {
        itemType.setId(id);
        return itemTypeRepositoryPort.updateItemType(id, itemType);
    }
}

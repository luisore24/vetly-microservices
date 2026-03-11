package com.company.microservice_catalog.application.usecase.itemtype;

import com.company.microservice_catalog.application.port.in.category.GetCategoryUseCase;
import com.company.microservice_catalog.application.port.in.itemtype.GetItemTypeUseCase;
import com.company.microservice_catalog.application.port.out.CategoryRepositoryPort;
import com.company.microservice_catalog.application.port.out.ItemTypeRepositoryPort;
import com.company.microservice_catalog.domain.model.Category;
import com.company.microservice_catalog.domain.model.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetItemTypeUseCaseService implements GetItemTypeUseCase {

    private final ItemTypeRepositoryPort itemTypeRepositoryPort;

    @Override
    public Optional<ItemType> getItemType(Long id) {
        return itemTypeRepositoryPort.findItemType(id);
    }

    @Override
    public List<ItemType> getAllItemsType() {
        return itemTypeRepositoryPort.findAllItemsType();
    }
}

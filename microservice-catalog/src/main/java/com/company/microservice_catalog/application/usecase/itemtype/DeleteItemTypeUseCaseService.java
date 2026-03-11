package com.company.microservice_catalog.application.usecase.itemtype;

import com.company.microservice_catalog.application.port.in.category.DeleteCategoryUseCase;
import com.company.microservice_catalog.application.port.in.itemtype.DeleteItemTypeUseCase;
import com.company.microservice_catalog.application.port.out.CategoryRepositoryPort;
import com.company.microservice_catalog.application.port.out.ItemTypeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteItemTypeUseCaseService implements DeleteItemTypeUseCase {

    private final ItemTypeRepositoryPort itemTypeRepositoryPort;

    @Override
    public void execute(Long id) {
        itemTypeRepositoryPort.deleteItemType(id);
    }
}

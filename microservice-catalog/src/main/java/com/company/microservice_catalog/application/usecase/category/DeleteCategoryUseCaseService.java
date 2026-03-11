package com.company.microservice_catalog.application.usecase.category;

import com.company.microservice_catalog.application.port.in.category.DeleteCategoryUseCase;
import com.company.microservice_catalog.application.port.in.product.DeleteProductUseCase;
import com.company.microservice_catalog.application.port.out.CategoryRepositoryPort;
import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCaseService implements DeleteCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public void execute(Long id) {
        categoryRepositoryPort.deleteCategory(id);
    }
}

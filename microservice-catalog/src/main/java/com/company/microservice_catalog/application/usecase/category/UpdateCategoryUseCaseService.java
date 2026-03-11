package com.company.microservice_catalog.application.usecase.category;

import com.company.microservice_catalog.application.port.in.category.UpdateCategoryUseCase;
import com.company.microservice_catalog.application.port.in.product.UpdateProductUseCase;
import com.company.microservice_catalog.application.port.out.CategoryRepositoryPort;
import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import com.company.microservice_catalog.domain.model.Category;
import com.company.microservice_catalog.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCaseService implements UpdateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    @Transactional
    public Category execute(Long id, Category category) {
        category.setId(id);
        return categoryRepositoryPort.updateCategory(id, category);
    }
}

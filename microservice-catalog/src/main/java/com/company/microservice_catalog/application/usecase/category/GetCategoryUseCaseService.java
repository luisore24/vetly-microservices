package com.company.microservice_catalog.application.usecase.category;

import com.company.microservice_catalog.application.port.in.category.GetCategoryUseCase;
import com.company.microservice_catalog.application.port.in.product.GetProductUseCase;
import com.company.microservice_catalog.application.port.out.CategoryRepositoryPort;
import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import com.company.microservice_catalog.domain.model.Category;
import com.company.microservice_catalog.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCategoryUseCaseService implements GetCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryRepositoryPort.findCategory(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepositoryPort.findAllCategories();
    }
}

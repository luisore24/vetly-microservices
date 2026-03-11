package com.company.microservice_catalog.application.usecase.category;

import com.company.microservice_catalog.application.port.in.category.CreateCategoryUseCase;
import com.company.microservice_catalog.application.port.out.CategoryRepositoryPort;
import com.company.microservice_catalog.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CreateCategoryUseCaseService implements CreateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    @Transactional
    public Category execute(Category category) {
        return categoryRepositoryPort.saveCategory(category);
    }
}

package com.company.microservice_catalog.application.port.out;

import com.company.microservice_catalog.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {

    Category saveCategory(Category category);

    Category updateCategory(Long id, Category category);

    Optional<Category> findCategory(Long id);

    List<Category> findAllCategories();

    void deleteCategory(Long id);
    
}

package com.company.microservice_catalog.application.port.in.category;

import com.company.microservice_catalog.domain.model.Category;
import com.company.microservice_catalog.domain.model.OfferedService;

import java.util.List;
import java.util.Optional;

public interface GetCategoryUseCase {

    Optional<Category> getCategory(Long id);

    List<Category> getAllCategories();

}

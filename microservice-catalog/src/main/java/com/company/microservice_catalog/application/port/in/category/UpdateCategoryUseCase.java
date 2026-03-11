package com.company.microservice_catalog.application.port.in.category;

import com.company.microservice_catalog.domain.model.Category;

public interface UpdateCategoryUseCase {

    Category execute(Long id, Category category);

}

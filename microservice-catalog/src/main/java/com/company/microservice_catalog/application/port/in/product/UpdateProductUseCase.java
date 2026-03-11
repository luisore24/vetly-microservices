package com.company.microservice_catalog.application.port.in.product;

import com.company.microservice_catalog.domain.model.Product;

public interface UpdateProductUseCase {

    Product execute(Long id, Product product);

}

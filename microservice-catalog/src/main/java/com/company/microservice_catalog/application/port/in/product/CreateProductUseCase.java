package com.company.microservice_catalog.application.port.in.product;

import com.company.microservice_catalog.domain.model.Product;

public interface CreateProductUseCase {

    Product execute(Product product);

}

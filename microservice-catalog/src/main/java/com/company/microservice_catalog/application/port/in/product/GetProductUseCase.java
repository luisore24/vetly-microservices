package com.company.microservice_catalog.application.port.in.product;

import com.company.microservice_catalog.domain.model.Product;

import java.util.Optional;
import java.util.List;

public interface GetProductUseCase {

    Optional<Product> getProduct(Long id);

    List<Product> getAllProducts();

}

package com.company.microservice_catalog.application.usecase.product;

import com.company.microservice_catalog.application.port.in.product.CreateProductUseCase;
import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import com.company.microservice_catalog.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    @Transactional
    public Product execute(Product product) {

        return productRepositoryPort.saveProduct(product);
    }
}

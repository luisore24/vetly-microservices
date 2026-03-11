package com.company.microservice_catalog.application.usecase.product;

import com.company.microservice_catalog.application.port.in.product.UpdateProductUseCase;
import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import com.company.microservice_catalog.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCaseService implements UpdateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    @Transactional
    public Product execute(Long id, Product product) {
        product.setId(id);
        return productRepositoryPort.updateProduct(id, product);
    }
}

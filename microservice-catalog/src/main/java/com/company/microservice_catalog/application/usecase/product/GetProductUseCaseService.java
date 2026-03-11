package com.company.microservice_catalog.application.usecase.product;

import com.company.microservice_catalog.application.port.in.product.GetProductUseCase;
import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import com.company.microservice_catalog.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetProductUseCaseService implements GetProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepositoryPort.findProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAllProducts();
    }
}

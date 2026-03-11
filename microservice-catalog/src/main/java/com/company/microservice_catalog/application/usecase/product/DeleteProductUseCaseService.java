package com.company.microservice_catalog.application.usecase.product;

import com.company.microservice_catalog.application.port.in.product.DeleteProductUseCase;
import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCaseService implements DeleteProductUseCase{

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public void execute(Long id) {
        productRepositoryPort.deleteProduct(id);
    }
}

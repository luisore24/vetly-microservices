package com.company.microservice_catalog.application.port.out;

import com.company.microservice_catalog.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product saveProduct(Product product);

    Product updateProduct(Long id, Product product);

    Optional<Product> findProduct(Long id);

    List<Product> findAllProducts();

    void deleteProduct(Long id);

}

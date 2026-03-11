package com.company.microservice_catalog.infrastructure.adapter.in.web;

import com.company.microservice_catalog.application.port.in.product.CreateProductUseCase;
import com.company.microservice_catalog.application.port.in.product.DeleteProductUseCase;
import com.company.microservice_catalog.application.port.in.product.GetProductUseCase;
import com.company.microservice_catalog.application.port.in.product.UpdateProductUseCase;
import com.company.microservice_catalog.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {


    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;


    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product){

        Product createdProduct = createProductUseCase.execute(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product product){

        Product updatedProduct = updateProductUseCase.execute(id, product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){

        Optional<Product> productFound = getProductUseCase.getProduct(id);

        return new ResponseEntity<>(productFound.get(), HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct(){

        List<Product> getProducts = getProductUseCase.getAllProducts();

        return new ResponseEntity<>(getProducts, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){

        deleteProductUseCase.execute(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}

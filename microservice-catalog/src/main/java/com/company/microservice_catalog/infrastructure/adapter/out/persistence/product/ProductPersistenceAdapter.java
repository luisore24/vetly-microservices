package com.company.microservice_catalog.infrastructure.adapter.out.persistence.product;

import com.company.microservice_catalog.application.port.out.ProductRepositoryPort;
import com.company.microservice_catalog.domain.model.*;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.catalog.CatalogItemSearchEntity;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.catalog.JpaCatalogItemSearchRepository;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.category.CategoryEntity;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type.ItemTypeEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {


    private final JpaProductRepository jpaProductRepository;
    private final JpaCatalogItemSearchRepository jpaCatalogItemSearchRepository;


    @Override
    @Transactional
    public Product saveProduct(Product product) {

        ProductEntity productEntity = ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .type(itemTypeDomainToEntity(product.getType()))
                .price(product.getPrice())
                .brand(product.getBrand())
                .category(categoryDomainToEntity(product.getCategory()))
                .usageInstruction(product.getUsageInstruction())
                .stock(product.getStock())
                .location(product.getLocation())
                .createAt(LocalDateTime.now())
                .createBy("ADMIN")
                .isDeleted(false)
                .build();


        ProductEntity productSaved = jpaProductRepository.save(productEntity);
        //product saved on products table

        //Begin save catalog_item table

        CatalogItemSearchEntity catalogItemSearch = CatalogItemSearchEntity.builder()
                .id(productSaved.getId())
                .name(productSaved.getName())
                .type(productSaved.getType())
                .price(productSaved.getPrice())
                .location(productSaved.getLocation())
                .status(productSaved.getStatus())
                .createAt(productSaved.getCreateAt())
                .createBy(productSaved.getCreateBy())
                .updateAt(productSaved.getUpdateAt())
                .updateBy(productSaved.getUpdateBy())
                .build();

        CatalogItemSearchEntity catalogItemSearchEntitySaved = jpaCatalogItemSearchRepository.save(catalogItemSearch);


        return productEntityToDomain(productSaved);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {


        Optional<ProductEntity> productFound = jpaProductRepository.findById(id);

        if(productFound.isEmpty()){
            return null;
        }

        ProductEntity productEntity = ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .type(itemTypeDomainToEntity(product.getType()))
                .price(product.getPrice())
                .brand(product.getBrand())
                .category(categoryDomainToEntity(product.getCategory()))
                .usageInstruction(product.getUsageInstruction())
                .stock(product.getStock())
                .location(product.getLocation())
                .createAt(productFound.get().getCreateAt())
                .createBy(productFound.get().getCreateBy())
                .updateAt(LocalDateTime.now())
                .updateBy("ADMIN")
                .isDeleted(productFound.get().getIsDeleted())
                .build();


        ProductEntity productUpdated = jpaProductRepository.save(productEntity);
        //product updated on products table

        //Begin save catalog_item table

        CatalogItemSearchEntity catalogItemSearch = CatalogItemSearchEntity.builder()
                .id(productUpdated.getId())
                .name(productUpdated.getName())
                .type(productUpdated.getType())
                .price(productUpdated.getPrice())
                .location(productUpdated.getLocation())
                .status(productUpdated.getStatus())
                .createAt(productUpdated.getCreateAt())
                .createBy(productUpdated.getCreateBy())
                .updateAt(productUpdated.getUpdateAt())
                .updateBy(productUpdated.getUpdateBy())
                .build();

        CatalogItemSearchEntity catalogItemSearchEntitySaved = jpaCatalogItemSearchRepository.save(catalogItemSearch);


        return productEntityToDomain(productUpdated);
    }


    @Override
    public Optional<Product> findProduct(Long id) {

        return jpaProductRepository.findById(id)
                .filter(prod -> !prod.getIsDeleted())
                .map(this::productEntityToDomain);
    }

    @Override
    public List<Product> findAllProducts() {
        return jpaProductRepository.findByIsDeletedFalse()
                .stream()
                .map(this::productEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {

        Optional<ProductEntity> productFound = jpaProductRepository.findById(id);

        if(productFound.isPresent()){

            ProductEntity productEntity = productFound.get();
            productEntity.setIsDeleted(true);

            //logic delete in products table - for audit
            ProductEntity productDeleted = jpaProductRepository.save(productEntity);

            //hard delete from catalog_item - For search

            jpaCatalogItemSearchRepository.deleteById(id);


        }

    }


    private CategoryEntity categoryDomainToEntity(Category category){
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private Category categoryEntityToDomain(CategoryEntity category){
        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }


    private ItemTypeEntity itemTypeDomainToEntity(ItemType type){
        return ItemTypeEntity.builder()
                .id(type.getId())
                .description(type.getDescription())
                .build();
    }

    private ItemType itemTypeEntityToDomain(ItemTypeEntity type){
        return ItemType.builder()
                .id(type.getId())
                .description(type.getDescription())
                .build();
    }


    private Product productEntityToDomain(ProductEntity product){
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .type(itemTypeEntityToDomain(product.getType()))
                .price(product.getPrice())
                .brand(product.getBrand())
                .category(categoryEntityToDomain(product.getCategory()))
                .usageInstruction(product.getUsageInstruction())
                .status(product.getStatus())
                .stock(product.getStock())
                .location(product.getLocation())
                .audit(toAuditData(
                        product.getCreateAt(),
                        product.getCreateBy(),
                        product.getUpdateAt(),
                        product.getUpdateBy()
                ))
                .build();
    }

    private AuditData toAuditData(LocalDateTime createAt, String createBy, LocalDateTime updateAt, String updateBy){
        return AuditData.builder()
                .create_at(createAt)
                .create_by(createBy)
                .update_at(updateAt)
                .update_by(updateBy)
                .build();
    }

}
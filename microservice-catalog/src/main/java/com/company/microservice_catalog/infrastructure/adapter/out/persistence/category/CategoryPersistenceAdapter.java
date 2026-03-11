package com.company.microservice_catalog.infrastructure.adapter.out.persistence.category;

import com.company.microservice_catalog.application.port.out.CategoryRepositoryPort;
import com.company.microservice_catalog.domain.model.AuditData;
import com.company.microservice_catalog.domain.model.Category;
import com.company.microservice_catalog.domain.model.ItemType;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type.ItemTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {

    private final JpaCategoryRepository jpaCategoryRepository;


    @Override
    @Transactional
    public Category saveCategory(Category category) {

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(category.getName())
                .description(category.getDescription())
                .type(itemTypeDomainToEntity(category.getType()))
                .status(true)
                .createAt(LocalDateTime.now())
                .createBy("ADMIN")
                .build();


        CategoryEntity categorySaved = jpaCategoryRepository.save(categoryEntity);

        return categoryEntityToDomain(categorySaved);
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category category) {

        Optional<CategoryEntity> categoryFound = jpaCategoryRepository.findById(id);

        if(categoryFound.isEmpty()){
            return null;
        }

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(categoryFound.get().getId())
                .name(category.getName())
                .description(category.getDescription())
                .type(itemTypeDomainToEntity(category.getType()))
                .status(category.getStatus())
                .updateAt(LocalDateTime.now())
                .updateBy("ADMIN")
                .build();


        CategoryEntity categoryUpdated = jpaCategoryRepository.save(categoryEntity);

        return categoryEntityToDomain(categoryUpdated);
    }

    @Override
    public Optional<Category> findCategory(Long id) {
        return jpaCategoryRepository.findById(id)
                .map(this::categoryEntityToDomain);
    }

    @Override
    public List<Category> findAllCategories() {
        return jpaCategoryRepository.findAll()
                .stream()
                .filter(CategoryEntity::getStatus)
                .map(this::categoryEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {

    }


    private ItemTypeEntity itemTypeDomainToEntity(ItemType type){
        return ItemTypeEntity.builder()
                .id(type.getId())
                .build();
    }

    private ItemType itemTypeEntityToDomain(ItemTypeEntity type){
        return ItemType.builder()
                .id(type.getId())
                .build();
    }

    private Category categoryEntityToDomain(CategoryEntity categoryEntity){
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .type(itemTypeEntityToDomain(categoryEntity.getType()))
                .status(categoryEntity.getStatus())
                .audit(toAuditData(categoryEntity.getCreateAt(),
                        categoryEntity.getCreateBy(),
                        categoryEntity.getUpdateAt(),
                        categoryEntity.getUpdateBy()))
                .build();
    }

    private Category categoryDomainToEntity(CategoryEntity categoryEntity){
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .type(itemTypeEntityToDomain(categoryEntity.getType()))
                .status(categoryEntity.getStatus())
                .audit(toAuditData(categoryEntity.getCreateAt(),
                        categoryEntity.getCreateBy(),
                        categoryEntity.getUpdateAt(),
                        categoryEntity.getUpdateBy()))
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

package com.company.microservice_catalog.infrastructure.adapter.out.persistence.service;

import com.company.microservice_catalog.application.port.out.OfferedServiceRepositoryPort;
import com.company.microservice_catalog.domain.model.*;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.catalog.CatalogItemSearchEntity;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.catalog.JpaCatalogItemSearchRepository;
import com.company.microservice_catalog.infrastructure.adapter.out.persistence.category.CategoryEntity;
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
public class ServicePersistenceAdapter implements OfferedServiceRepositoryPort {

    private final JpaServiceRepository jpaServiceRepository;
    private final JpaCatalogItemSearchRepository jpaCatalogItemSearchRepository;

    @Override
    @Transactional
    public OfferedService saveService(OfferedService offeredService) {
        ServiceEntity serviceEntity = ServiceEntity.builder()
                .name(offeredService.getName())
                .description(offeredService.getDescription())
                .type(itemTypeDomainToEntity(offeredService.getType()))
                .price(offeredService.getPrice())
                .category(categoryDomainToEntity(offeredService.getCategory()))
                .estimatedDurationMin(offeredService.getEstimatedDurationMin())
                .considerations(offeredService.getConsiderations())
                .location(offeredService.getLocation())
                .createAt(LocalDateTime.now())
                .createBy("ADMIN")
                .isDeleted(false)
                .build();


        ServiceEntity serviceSaved = jpaServiceRepository.save(serviceEntity);
        //service saved on services table

        //Begin save catalog_item table

        CatalogItemSearchEntity catalogItemSearch = CatalogItemSearchEntity.builder()
                .id(serviceSaved.getId())
                .name(serviceSaved.getName())
                .type(serviceSaved.getType())
                .price(serviceSaved.getPrice())
                .location(serviceSaved.getLocation())
                .status(serviceSaved.getStatus())
                .createAt(serviceSaved.getCreateAt())
                .createBy(serviceSaved.getCreateBy())
                .updateAt(serviceSaved.getUpdateAt())
                .updateBy(serviceSaved.getUpdateBy())
                .build();

        CatalogItemSearchEntity catalogItemSearchEntitySaved = jpaCatalogItemSearchRepository.save(catalogItemSearch);


        return serviceEntityToDomain(serviceSaved);
    }

    @Override
    @Transactional
    public OfferedService updateService(Long id, OfferedService offeredService) {
        Optional<ServiceEntity> serviceFound = jpaServiceRepository.findById(id);

        if(serviceFound.isEmpty()){
            return null;
        }

        ServiceEntity serviceEntity = ServiceEntity.builder()
                .id(offeredService.getId())
                .name(offeredService.getName())
                .description(offeredService.getDescription())
                .type(itemTypeDomainToEntity(offeredService.getType()))
                .price(offeredService.getPrice())
                .category(categoryDomainToEntity(offeredService.getCategory()))
                .estimatedDurationMin(offeredService.getEstimatedDurationMin())
                .considerations(offeredService.getConsiderations())
                .location(offeredService.getLocation())
                .createAt(serviceFound.get().getCreateAt())
                .createBy(serviceFound.get().getCreateBy())
                .updateAt(LocalDateTime.now())
                .updateBy("ADMIN")
                .isDeleted(serviceFound.get().getIsDeleted())
                .build();


        ServiceEntity serviceUpdated = jpaServiceRepository.save(serviceEntity);
        //service updated on services table

        //Begin save catalog_item table

        CatalogItemSearchEntity catalogItemSearch = CatalogItemSearchEntity.builder()
                .id(serviceUpdated.getId())
                .name(serviceUpdated.getName())
                .type(serviceUpdated.getType())
                .price(serviceUpdated.getPrice())
                .location(serviceUpdated.getLocation())
                .status(serviceUpdated.getStatus())
                .createAt(serviceUpdated.getCreateAt())
                .createBy(serviceUpdated.getCreateBy())
                .updateAt(serviceUpdated.getUpdateAt())
                .updateBy(serviceUpdated.getUpdateBy())
                .build();

        CatalogItemSearchEntity catalogItemSearchEntitySaved = jpaCatalogItemSearchRepository.save(catalogItemSearch);


        return serviceEntityToDomain(serviceUpdated);
    }

    @Override
    public Optional<OfferedService> findService(Long id) {
        return jpaServiceRepository.findById(id)
                .filter(serv -> !serv.getIsDeleted())
                .map(this::serviceEntityToDomain);
    }

    @Override
    public List<OfferedService> findAllServices() {
        return jpaServiceRepository.findByIsDeletedFalse()
                .stream()
                .map(this::serviceEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteService(Long id) {
        Optional<ServiceEntity> serviceFound = jpaServiceRepository.findById(id);

        if(serviceFound.isPresent()){

            ServiceEntity serviceEntity = serviceFound.get();
            serviceEntity.setIsDeleted(true);

            //logic delete in services table - for audit
            ServiceEntity serviceDeleted = jpaServiceRepository.save(serviceEntity);

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


    private OfferedService serviceEntityToDomain(ServiceEntity service){
        return OfferedService.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .type(itemTypeEntityToDomain(service.getType()))
                .price(service.getPrice())
                .category(categoryEntityToDomain(service.getCategory()))
                .estimatedDurationMin(service.getEstimatedDurationMin())
                .considerations(service.getConsiderations())
                .status(service.getStatus())
                .location(service.getLocation())
                .audit(toAuditData(
                        service.getCreateAt(),
                        service.getCreateBy(),
                        service.getUpdateAt(),
                        service.getUpdateBy()
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

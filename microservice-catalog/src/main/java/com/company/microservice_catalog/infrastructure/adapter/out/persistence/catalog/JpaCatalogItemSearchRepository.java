package com.company.microservice_catalog.infrastructure.adapter.out.persistence.catalog;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCatalogItemSearchRepository extends JpaRepository<CatalogItemSearchEntity, Long> {

    List<CatalogItemSearchEntity> findByNameContainingIgnoreCase(String name);

}

package com.company.microservice_catalog.infrastructure.adapter.out.persistence.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    //List<CategoryEntity> findByIsDeletedFalse();

}

package com.company.microservice_catalog.infrastructure.adapter.out.persistence.item_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaItemTypeRepository extends JpaRepository<ItemTypeEntity, Long> {

    //List<ItemTypeEntity> findByIsDeletedFalse();

}

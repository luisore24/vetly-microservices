package com.company.microservice_catalog.infrastructure.adapter.out.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaServiceRepository extends JpaRepository<ServiceEntity, Long> {

    List<ServiceEntity> findByIsDeletedFalse();

}

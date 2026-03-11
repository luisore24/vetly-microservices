package com.company.microservice_auth.repository.status;

import com.company.microservice_auth.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Long> {

    Optional<Status> findByDescription(String description);

}

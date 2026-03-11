package com.company.microservice_auth.repository.permission;

import com.company.microservice_auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long>  {


    Optional<Permission> findByDescription(String permission);

}

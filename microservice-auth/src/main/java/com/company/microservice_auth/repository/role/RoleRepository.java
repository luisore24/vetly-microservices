package com.company.microservice_auth.repository.role;

import com.company.microservice_auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByDescription(String role);
}

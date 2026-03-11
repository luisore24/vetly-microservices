package com.company.microservice_auth.repository.menu;

import com.company.microservice_auth.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByDescription(String menu);

}

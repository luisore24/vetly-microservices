package com.company.microservice_auth.repository;

import com.company.microservice_auth.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {


//    @Query("SELECT DISTINCT u FROM User u " +
//            "JOIN FETCH u.roles ur " +
//            "JOIN FETCH ur.role r " +
//            "JOIN FETCH r.rolePermissions rp " +
//            "JOIN FETCH rp.permission p " +
//            "JOIN FETCH r.roleMenus rm " +
//            "JOIN FETCH rm.menu m " +
//            "WHERE u.username = :username " +
//            "AND ur.status.description = 'ACTIVO' " +
//            "AND r.status.description = 'ACTIVO' " +
//            "AND rp.status.description = 'ACTIVO' " +
//            "AND p.status.description = 'ACTIVO' " +
//            "AND rm.status.description = 'ACTIVO' " +
//            "AND m.status.description = 'ACTIVO'")
//    Optional<User> findByUsernameWithRolesPermissionsMenus(@Param("username") String username);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN FETCH u.roles ur " +
            "JOIN FETCH ur.role r " +
            "JOIN FETCH r.rolePermissions rp " +
            "JOIN FETCH rp.permission p " +
            "WHERE u.username = :username " +
            "AND u.isDeleted = false")
    Optional<User> findByUsernameWithRolesPermissions(@Param("username") String username);

}

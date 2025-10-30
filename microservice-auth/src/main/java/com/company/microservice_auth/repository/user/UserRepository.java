package com.company.microservice_auth.repository.user;

import com.company.microservice_auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "UPDATE users u SET " +
            "u.is_deleted = true, " +
            "u.is_enable = false, " +
            "u.account_no_locked = false, " +
            "u.account_no_expired = false, " +
            "u.credential_no_expired = false " +
            "u.update_at = CURRENT_TIMESTAMP " +
            "u.update_by = :updateBy " +
            "WHERE id = :id", nativeQuery = true)
    void removeUser(@Param("id") Long id, @Param("updateBy") String updateBy);



    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndIsDeletedFalse(Long id);

    List<User> findAllByIsDeletedFalse();




}

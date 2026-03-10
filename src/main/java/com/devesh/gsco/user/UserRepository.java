package com.devesh.gsco.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("update User u set u.name = :name where u.userId = :userId")
    int updateName(String name, Integer userId);

    @Modifying
    @Transactional
    @Query("update User u set u.password = :password where u.userId = :userId")
    int updatePassword(String password, Integer userId);
}

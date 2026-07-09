package com.apiwatch.user_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.apiwatch.user_service.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByAuthUserId(UUID authUserId);

    boolean existsByEmail(String email);

    boolean existsByAuthUserId(UUID authUserId);

}
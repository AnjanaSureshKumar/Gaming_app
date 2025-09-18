package com.example.GamingApp.repositories;

import com.example.GamingApp.entities.admin_users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface admin_usersRepository extends MongoRepository<admin_users, String> {

    // Exact match by username
    Optional<admin_users> findByUsername(String username);

    // Partial match, case-insensitive
    List<admin_users> findByUsernameContainingIgnoreCase(String username);
}

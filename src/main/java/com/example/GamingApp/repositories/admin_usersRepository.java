package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.admin_users;

public interface admin_usersRepository extends MongoRepository<admin_users, String> {}


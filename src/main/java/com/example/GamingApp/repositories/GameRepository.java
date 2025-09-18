package com.example.GamingApp.repositories;

import com.example.GamingApp.entities.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends MongoRepository<Game, String> {
    Optional<Game> findByName(String name);
    List<Game> findByNameContainingIgnoreCase(String name);

    // New: find by fields
    List<Game> findByPrice(float price);
    List<Game> findByDescriptionContainingIgnoreCase(String description);
    List<Game> findByDuration(String duration);
    List<Game> findByStatus(String status);
}

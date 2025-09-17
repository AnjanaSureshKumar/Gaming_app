package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.Game;

public interface GameRepository extends MongoRepository<Game, String> {}

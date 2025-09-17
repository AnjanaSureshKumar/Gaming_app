package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.CollectionSummary;

public interface CollectionRepository extends MongoRepository<CollectionSummary, String> {}


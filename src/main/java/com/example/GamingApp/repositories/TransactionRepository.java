package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {}

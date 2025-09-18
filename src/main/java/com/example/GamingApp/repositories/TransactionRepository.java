package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    Optional<Transaction> findByMemberId(String memberId);
    List<Transaction> findByMemberIdContainingIgnoreCase(String memberId);

    Optional<Transaction> findByGameId(String gameId);
    List<Transaction> findByGameIdContainingIgnoreCase(String gameId);

    List<Transaction> findByAmount(float amount);
    List<Transaction> findByDateTime(java.util.Date dateTime);
}

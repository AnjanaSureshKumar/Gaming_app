package com.example.GamingApp.repositories;

import com.example.GamingApp.entities.CollectionSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CollectionRepository extends MongoRepository<CollectionSummary, String> {

    // Find all by exact date
    List<CollectionSummary> findByDate(Date date);

    // Find all by exact totalRecharges
    List<CollectionSummary> findByTotalRecharges(float totalRecharges);

    // Find all by date and totalRecharges
    List<CollectionSummary> findByDateAndTotalRecharges(Date date, float totalRecharges);
}

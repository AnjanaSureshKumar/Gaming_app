package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.Recharge;

public interface RechargeRepository extends MongoRepository<Recharge, String> {}

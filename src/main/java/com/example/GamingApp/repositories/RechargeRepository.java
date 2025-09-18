package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.Recharge;
import java.util.List;
import java.util.Optional;

public interface RechargeRepository extends MongoRepository<Recharge, String> {

    Optional<Recharge> findByMemberId(String memberId);
    List<Recharge> findByMemberIdContainingIgnoreCase(String memberId);

    List<Recharge> findByAmount(float amount);
}

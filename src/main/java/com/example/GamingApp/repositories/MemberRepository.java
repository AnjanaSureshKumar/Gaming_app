package com.example.GamingApp.repositories;

import com.example.GamingApp.entities.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByName(String name);           // exact match for update/delete
    List<Member> findByNameContainingIgnoreCase(String name); // optional search
    List<Member> findByPhone(String phone);
    List<Member> findByBalance(float balance);
    List<Member> findByJoiningDate(LocalDate joiningDate);
}

package com.example.GamingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.GamingApp.entities.Member;

public interface MemberRepository extends MongoRepository<Member, String> {}

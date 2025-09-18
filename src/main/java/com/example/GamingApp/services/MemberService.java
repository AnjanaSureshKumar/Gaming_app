package com.example.GamingApp.services;

import com.example.GamingApp.entities.Member;
import com.example.GamingApp.repositories.MemberRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repo;
    private static final Logger log = LoggerFactory.getLogger(MemberService.class);

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    public Member create(Member member) {
        member.setId(null);
        if (member.getName() == null || member.getName().isBlank()) {
            throw new BusinessException("Name is required");
        }
        if (member.getPhone() == null || member.getPhone().isBlank()) {
            throw new BusinessException("Phone is required");
        }
        log.info("Creating member: {}", member.getName());
        return repo.save(member);
    }

    public List<Member> findAll() {
        log.info("Fetching all members");
        return repo.findAll();
    }

    public Member findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));
    }

    public Member update(String id, Member updated) {
        Member existing = findById(id);
        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        existing.setBalance(updated.getBalance());
        existing.setJoiningDate(updated.getJoiningDate());
        log.info("Updating member: {}", id);
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Member not found: " + id);
        }
        log.info("Deleting member: {}", id);
        repo.deleteById(id);
    }
}

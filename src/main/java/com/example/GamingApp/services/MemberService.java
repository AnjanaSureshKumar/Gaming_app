package com.example.GamingApp.services;

import com.example.GamingApp.entities.Member;
import com.example.GamingApp.repositories.MemberRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Member create(Member member) {
        member.setId(null);
        if (member.getName() == null || member.getName().isBlank())
            throw new BusinessException("Name required");
        if (member.getPhone() == null || member.getPhone().isBlank())
            throw new BusinessException("Phone required");
        return repo.save(member);
    }

    // READ ALL
    public List<Member> findAll() {
        return repo.findAll();
    }

    // READ BY ID
    public Member findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));
    }

    // UPDATE BY ID
    public Member updateById(String id, Member updated) {
        Member existing = findById(id);
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getPhone() != null) existing.setPhone(updated.getPhone());
        if (updated.getBalance() >= 0) existing.setBalance(updated.getBalance());
        if (updated.getJoiningDate() != null) existing.setJoiningDate(updated.getJoiningDate());
        return repo.save(existing);
    }

    // UPDATE BY NAME
    public Member updateByName(String name, Member updated) {
        Member existing = repo.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with name: " + name));
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getPhone() != null) existing.setPhone(updated.getPhone());
        if (updated.getBalance() >= 0) existing.setBalance(updated.getBalance());
        if (updated.getJoiningDate() != null) existing.setJoiningDate(updated.getJoiningDate());
        return repo.save(existing);
    }

    // DELETE BY ID
    public void deleteById(String id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Member not found: " + id);
        repo.deleteById(id);
    }

    // DELETE BY NAME
    public void deleteByName(String name) {
        Member existing = repo.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with name: " + name));
        repo.delete(existing);
    }

    // SEARCH METHODS
    public List<Member> searchByName(String nameLike) {
        List<Member> result = repo.findAll().stream()
                .filter(m -> m.getName() != null && m.getName().toLowerCase().contains(nameLike.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty())
            throw new ResourceNotFoundException("No members found with name containing: " + nameLike);
        return result;
    }

    public List<Member> searchByPhone(String phone) {
        List<Member> result = repo.findAll().stream()
                .filter(m -> m.getPhone() != null && m.getPhone().equals(phone))
                .collect(Collectors.toList());
        if (result.isEmpty())
            throw new ResourceNotFoundException("No members found with phone: " + phone);
        return result;
    }

    public List<Member> searchByBalance(float balance) {
        List<Member> result = repo.findAll().stream()
                .filter(m -> m.getBalance() == balance)
                .collect(Collectors.toList());
        if (result.isEmpty())
            throw new ResourceNotFoundException("No members found with balance: " + balance);
        return result;
    }

    public List<Member> searchByJoiningDate(Date joiningDate) {
        List<Member> result = repo.findAll().stream()
                .filter(m -> m.getJoiningDate() != null && m.getJoiningDate().equals(joiningDate))
                .collect(Collectors.toList());
        if (result.isEmpty())
            throw new ResourceNotFoundException("No members found with joining date: " + joiningDate);
        return result;
    }
}

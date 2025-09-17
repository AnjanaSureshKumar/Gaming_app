package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Member;
import com.example.GamingApp.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberRepository repo;

    @PostMapping
    public Member create(@RequestBody Member member) {
        member.setId(null);
        return repo.save(member);
    }

    @GetMapping
    public List<Member> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable String id, @RequestBody Member member) {
        Optional<Member> optional = repo.findById(id);
        if (optional.isEmpty()) return null;

        Member oldMember = optional.get();
        oldMember.setName(member.getName());
        oldMember.setPhone(member.getPhone());
        oldMember.setBalance(member.getBalance());
        oldMember.setJoiningDate(member.getJoiningDate());

        return repo.save(oldMember);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}

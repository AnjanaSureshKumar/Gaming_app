package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Member;
import com.example.GamingApp.services.MemberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(member));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // UPDATE BY ID
    @PutMapping("/id/{id}")
    public ResponseEntity<Member> updateById(@PathVariable String id, @RequestBody Member member) {
        return ResponseEntity.ok(service.updateById(id, member));
    }

    // UPDATE BY NAME
    @PutMapping("/name/{name}")
    public ResponseEntity<Member> updateByName(@PathVariable String name, @RequestBody Member member) {
        return ResponseEntity.ok(service.updateByName(name, member));
    }

    // DELETE BY ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE BY NAME
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        service.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    // SEARCH BY JOINING DATE
    @GetMapping("/search/joiningDate")
    public ResponseEntity<List<Member>> searchByJoiningDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date joiningDate) {
        return ResponseEntity.ok(service.searchByJoiningDate(joiningDate));
    }
}

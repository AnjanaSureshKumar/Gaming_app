package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.admin_users;
import com.example.GamingApp.services.admin_usersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_user")
public class admin_usersController {

    private final admin_usersService service;

    public admin_usersController(admin_usersService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<admin_users> create(@RequestBody admin_users user) {
        admin_users created = service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<admin_users>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<admin_users> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<admin_users> update(@PathVariable String id, @RequestBody admin_users user) {
        return ResponseEntity.ok(service.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

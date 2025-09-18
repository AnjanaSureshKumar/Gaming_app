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

    // CREATE
    @PostMapping
    public ResponseEntity<admin_users> create(@RequestBody admin_users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(user));
    }

    // READ ALL or SEARCH
    @GetMapping
    public ResponseEntity<List<admin_users>> findAll(
            @RequestParam(required = false, name = "username_like") String usernameLike) {

        if (usernameLike != null && !usernameLike.isBlank()) {
            // This will return only users containing the username_like string
            return ResponseEntity.ok(service.searchByUsername(usernameLike));
        }

        // No query param → return all users
        return ResponseEntity.ok(service.findAll());
    }

    // Optional: Keep legacy /search endpoint
    @GetMapping("/search")
    public ResponseEntity<List<admin_users>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByUsername(name));
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<admin_users> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<admin_users> update(@PathVariable String id, @RequestBody admin_users user) {
        return ResponseEntity.ok(service.update(id, user));
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE BY USERNAME
    @PutMapping("/username/{username}")
    public ResponseEntity<admin_users> updateByUsername(@PathVariable String username,
                                                        @RequestBody admin_users user) {
        return ResponseEntity.ok(service.updateByUsername(username, user));
    }

    // DELETE BY USERNAME
    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
        service.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }
}

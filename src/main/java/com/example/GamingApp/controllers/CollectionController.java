package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.CollectionSummary;
import com.example.GamingApp.services.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService service;

    public CollectionController(CollectionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CollectionSummary> create(@RequestBody CollectionSummary summary) {
        CollectionSummary created = service.create(summary);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<CollectionSummary>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionSummary> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionSummary> update(@PathVariable String id, @RequestBody CollectionSummary summary) {
        return ResponseEntity.ok(service.update(id, summary));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

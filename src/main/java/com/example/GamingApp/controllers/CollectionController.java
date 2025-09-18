package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.CollectionSummary;
import com.example.GamingApp.services.CollectionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService service;

    public CollectionController(CollectionService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CollectionSummary> create(@RequestBody CollectionSummary summary) {
        CollectionSummary created = service.create(summary);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL with optional filters: date, totalRecharges
    @GetMapping
    public ResponseEntity<List<CollectionSummary>> findAll(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam(required = false) Float totalRecharges) {
        return ResponseEntity.ok(service.findAll(date, totalRecharges));
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CollectionSummary> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<CollectionSummary> update(@PathVariable String id,
                                                    @RequestBody CollectionSummary summary) {
        return ResponseEntity.ok(service.update(id, summary));
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

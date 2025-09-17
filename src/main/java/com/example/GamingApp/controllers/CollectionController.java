package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.CollectionSummary;
import com.example.GamingApp.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CollectionRepository repo;

    @PostMapping
    public CollectionSummary create(@RequestBody CollectionSummary summary) {
        summary.setId(null);
        return repo.save(summary);
    }

    @GetMapping
    public List<CollectionSummary> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public CollectionSummary findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public CollectionSummary update(@PathVariable String id, @RequestBody CollectionSummary summary) {
        Optional<CollectionSummary> optional = repo.findById(id);
        if (optional.isEmpty()) return null;

        CollectionSummary oldSummary = optional.get();
        oldSummary.setDate(summary.getDate());
        oldSummary.setTotalRecharges(summary.getTotalRecharges());

        return repo.save(oldSummary);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}

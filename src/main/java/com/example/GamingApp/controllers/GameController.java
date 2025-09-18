package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Game;
import com.example.GamingApp.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return ResponseEntity.status(201).body(service.create(game));
    }

    // GET all or search by name
    @GetMapping
    public ResponseEntity<List<Game>> findAll(@RequestParam(required = false, name = "name_like") String nameLike) {
        if (nameLike != null && !nameLike.isBlank()) {
            return ResponseEntity.ok(service.searchByName(nameLike));
        }
        return ResponseEntity.ok(service.findAll());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // UPDATE by ID
    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable String id, @RequestBody Game game) {
        return ResponseEntity.ok(service.update(id, game));
    }

    // DELETE by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Optional: UPDATE by name
    @PutMapping("/name/{name}")
    public ResponseEntity<Game> updateByName(@PathVariable String name, @RequestBody Game game) {
        return ResponseEntity.ok(service.updateByName(name, game));
    }

    // Optional: DELETE by name
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        service.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
    // GET by exact name
@GetMapping("/name/{name}")
public ResponseEntity<Game> getByName(@PathVariable String name) {
    return ResponseEntity.ok(service.updateByName(name, null)); // or create a findByName method in service
}

// GET by price
@GetMapping("/price/{price}")
public ResponseEntity<List<Game>> getByPrice(@PathVariable float price) {
    return ResponseEntity.ok(service.findByPrice(price));
}

// GET by description
@GetMapping("/description/{description}")
public ResponseEntity<List<Game>> getByDescription(@PathVariable String description) {
    return ResponseEntity.ok(service.findByDescription(description));
}

// GET by duration
@GetMapping("/duration/{duration}")
public ResponseEntity<List<Game>> getByDuration(@PathVariable String duration) {
    return ResponseEntity.ok(service.findByDuration(duration));
}

// GET by status
@GetMapping("/status/{status}")
public ResponseEntity<List<Game>> getByStatus(@PathVariable String status) {
    return ResponseEntity.ok(service.findByStatus(status));
}

}

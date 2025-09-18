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

    @GetMapping
    public ResponseEntity<List<Game>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable String id, @RequestBody Game game) {
        return ResponseEntity.ok(service.update(id, game));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

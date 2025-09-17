package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Game;
import com.example.GamingApp.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository repo;

    @PostMapping
    public Game create(@RequestBody Game game) {
        game.setId(null);
        return repo.save(game);
    }

    @GetMapping
    public List<Game> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable String id, @RequestBody Game game) {
        Optional<Game> optional = repo.findById(id);
        if (optional.isEmpty()) return null;

        Game oldGame = optional.get();
        oldGame.setName(game.getName());
        oldGame.setPrice(game.getPrice());
        oldGame.setDescription(game.getDescription());
        oldGame.setDuration(game.getDuration());
        oldGame.setStatus(game.getStatus());

        return repo.save(oldGame);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}

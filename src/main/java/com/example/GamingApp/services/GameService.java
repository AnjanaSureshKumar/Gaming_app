package com.example.GamingApp.services;

import com.example.GamingApp.entities.Game;
import com.example.GamingApp.repositories.GameRepository;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import com.example.GamingApp.exceptions.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    private final GameRepository repo;

    public GameService(GameRepository repo) {
        this.repo = repo;
    }

    public Game create(Game game) {
        log.info("Creating game: {}", game.getName());
        game.setId(null);
        validate(game);
        return repo.save(game);
    }

    public List<Game> findAll() {
        log.info("Fetching all games");
        return repo.findAll();
    }

    public Game findById(String id) {
        log.info("Fetching game with id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Game not found with id {}", id);
                    return new ResourceNotFoundException("Game not found: " + id);
                });
    }

    public Game update(String id, Game game) {
        log.info("Updating game {}", id);
        Game existing = repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Game not found with id {}", id);
                    return new ResourceNotFoundException("Game not found: " + id);
                });

        existing.setName(game.getName());
        existing.setPrice(game.getPrice());
        existing.setDescription(game.getDescription());
        existing.setDuration(game.getDuration());
        existing.setStatus(game.getStatus());

        validate(existing);
        return repo.save(existing);
    }

    public void delete(String id) {
        log.info("Deleting game {}", id);
        if (!repo.existsById(id)) {
            log.error("Cannot delete, game not found: {}", id);
            throw new ResourceNotFoundException("Game not found: " + id);
        }
        repo.deleteById(id);
    }

    private void validate(Game game) {
        if (game.getPrice() < 0) {
            log.error("Invalid price: {}", game.getPrice());
            throw new BusinessException("Price cannot be negative");
        }
        if (game.getName() == null || game.getName().isBlank()) {
            log.error("Game name is missing");
            throw new BusinessException("Game name is required");
        }
    }
    public Game updateByName(String name, Game updated) {
    Game existing = repo.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Game not found: " + name));

    existing.setName(updated.getName());
    existing.setPrice(updated.getPrice());
    existing.setDescription(updated.getDescription());
    existing.setDuration(updated.getDuration());
    existing.setStatus(updated.getStatus());

    validate(existing);
    return repo.save(existing);
}

public void deleteByName(String name) {
    Game existing = repo.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Game not found: " + name));
    repo.delete(existing);
}
public List<Game> searchByName(String name) {
    List<Game> games = repo.findByNameContainingIgnoreCase(name);
    if (games.isEmpty()) {
        throw new ResourceNotFoundException("No games found containing: " + name);
    }
    return games;
}
public List<Game> findByPrice(float price) {
    List<Game> games = repo.findByPrice(price);
    if (games.isEmpty()) throw new ResourceNotFoundException("No games found with price: " + price);
    return games;
}

public List<Game> findByDescription(String description) {
    List<Game> games = repo.findByDescriptionContainingIgnoreCase(description);
    if (games.isEmpty()) throw new ResourceNotFoundException("No games found with description containing: " + description);
    return games;
}

public List<Game> findByDuration(String duration) {
    List<Game> games = repo.findByDuration(duration);
    if (games.isEmpty()) throw new ResourceNotFoundException("No games found with duration: " + duration);
    return games;
}

public List<Game> findByStatus(String status) {
    List<Game> games = repo.findByStatus(status);
    if (games.isEmpty()) throw new ResourceNotFoundException("No games found with status: " + status);
    return games;
}


}

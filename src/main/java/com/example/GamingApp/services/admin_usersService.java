package com.example.GamingApp.services;

import com.example.GamingApp.entities.admin_users;
import com.example.GamingApp.repositories.admin_usersRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class admin_usersService {

    private final admin_usersRepository repo;
    private static final Logger log = LoggerFactory.getLogger(admin_usersService.class);

    public admin_usersService(admin_usersRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public admin_users create(admin_users user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new BusinessException("Username is required");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BusinessException("Password is required");
        }
        user.setId(null);
        log.info("Creating admin user: {}", user.getUsername());
        return repo.save(user);
    }

    // READ ALL
    public List<admin_users> findAll() {
        return repo.findAll();
    }

    // READ BY ID
    public admin_users findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found: " + id));
    }

    // UPDATE BY ID
    public admin_users update(String id, admin_users updated) {
        admin_users existing = findById(id);
        if (updated.getUsername() != null && !updated.getUsername().isBlank()) {
            existing.setUsername(updated.getUsername());
        }
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(updated.getPassword());
        }
        return repo.save(existing);
    }

    // DELETE BY ID
    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Admin user not found: " + id);
        }
        repo.deleteById(id);
    }

    // SEARCH BY USERNAME (partial match)
    public List<admin_users> searchByUsername(String username) {
        List<admin_users> users = repo.findByUsernameContainingIgnoreCase(username);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No admin users found containing: " + username);
        }
        return users;
    }

    // UPDATE BY USERNAME
    public admin_users updateByUsername(String username, admin_users updated) {
        admin_users existing = repo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found: " + username));
        if (updated.getUsername() != null && !updated.getUsername().isBlank()) {
            existing.setUsername(updated.getUsername());
        }
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(updated.getPassword());
        }
        return repo.save(existing);
    }

    // DELETE BY USERNAME
    public void deleteByUsername(String username) {
        admin_users existing = repo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found: " + username));
        repo.delete(existing);
    }
}

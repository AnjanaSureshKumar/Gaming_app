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

    public admin_users create(admin_users user) {
        user.setId(null);
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            log.error("Username is required");
            throw new BusinessException("Username is required");
        }
        log.info("Creating admin user: {}", user.getUsername());
        return repo.save(user);
    }

    public List<admin_users> findAll() {
        log.info("Fetching all admin users");
        return repo.findAll();
    }

    public admin_users findById(String id) {
        log.info("Fetching admin user with id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found: " + id));
    }

    public admin_users update(String id, admin_users updated) {
        admin_users existing = findById(id);
        existing.setUsername(updated.getUsername());
        existing.setPassword(updated.getPassword());
        log.info("Updating admin user {}", id);
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            log.error("Admin user not found with id {}", id);
            throw new ResourceNotFoundException("Admin user not found: " + id);
        }
        log.info("Deleting admin user {}", id);
        repo.deleteById(id);
    }
}

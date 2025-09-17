package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.admin_users;
import com.example.GamingApp.repositories.admin_usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin_user")
public class admin_usersController {

    @Autowired
    private admin_usersRepository repo;

    @PostMapping
    public admin_users create(@RequestBody admin_users user) {   // return entity, not repo
        user.setId(null);
        return repo.save(user);
    }

    @GetMapping
    public List<admin_users> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public admin_users findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public admin_users update(@PathVariable String id, @RequestBody admin_users summary) {
        Optional<admin_users> optional = repo.findById(id);
        if (optional.isEmpty()) return null;

        admin_users oldSummary = optional.get();
        oldSummary.setUsername(summary.getUsername());
        oldSummary.setPassword(summary.getPassword());

        return repo.save(oldSummary);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}

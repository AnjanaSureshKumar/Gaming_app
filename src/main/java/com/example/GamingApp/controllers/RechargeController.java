package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Recharge;
import com.example.GamingApp.repositories.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    @Autowired
    private RechargeRepository repo;

    @PostMapping
    public Recharge create(@RequestBody Recharge recharge) {
        recharge.setId(null);
        return repo.save(recharge);
    }

    @GetMapping
    public List<Recharge> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Recharge findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Recharge update(@PathVariable String id, @RequestBody Recharge recharge) {
        Optional<Recharge> optional = repo.findById(id);
        if (optional.isEmpty()) return null;

        Recharge oldRecharge = optional.get();
        oldRecharge.setMemberId(recharge.getMemberId());
        oldRecharge.setAmount(recharge.getAmount());

        return repo.save(oldRecharge);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}

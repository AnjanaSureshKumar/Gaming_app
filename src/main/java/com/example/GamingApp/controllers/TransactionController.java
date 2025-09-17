package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Transaction;
import com.example.GamingApp.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository repo;

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        transaction.setId(null);
        return repo.save(transaction);
    }

    @GetMapping
    public List<Transaction> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable String id, @RequestBody Transaction transaction) {
        Optional<Transaction> optional = repo.findById(id);
        if (optional.isEmpty()) return null;

        Transaction oldTransaction = optional.get();
        oldTransaction.setMemberId(transaction.getMemberId());
        oldTransaction.setGameId(transaction.getGameId());
        oldTransaction.setAmount(transaction.getAmount());
        oldTransaction.setDateTime(transaction.getDateTime());

        return repo.save(oldTransaction);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}

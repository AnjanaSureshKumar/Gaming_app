package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Transaction;
import com.example.GamingApp.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transaction));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Transaction>> getByMemberId(@PathVariable String memberId) {
        return ResponseEntity.ok(service.findByMemberId(memberId));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Transaction>> getByGameId(@PathVariable String gameId) {
        return ResponseEntity.ok(service.findByGameId(gameId));
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<Transaction>> getByAmount(@PathVariable float amount) {
        return ResponseEntity.ok(service.findByAmount(amount));
    }

    @GetMapping("/date/{dateTime}")
    public ResponseEntity<List<Transaction>> getByDate(@PathVariable Date dateTime) {
        return ResponseEntity.ok(service.findByDateTime(dateTime));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Transaction> update(@PathVariable String id, @RequestBody Transaction transaction) {
        return ResponseEntity.ok(service.update(id, transaction));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
  
}

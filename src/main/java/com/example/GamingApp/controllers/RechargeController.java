package com.example.GamingApp.controllers;

import com.example.GamingApp.entities.Recharge;
import com.example.GamingApp.services.RechargeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    private final RechargeService service;

    public RechargeController(RechargeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Recharge> create(@RequestBody Recharge recharge) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(recharge));
    }

    @GetMapping
    public ResponseEntity<List<Recharge>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Recharge> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // GET by memberId
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Recharge>> getByMemberId(@PathVariable String memberId) {
        return ResponseEntity.ok(service.findByMemberId(memberId));
    }

    // GET by amount
    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<Recharge>> getByAmount(@PathVariable float amount) {
        return ResponseEntity.ok(service.findByAmount(amount));
    }

    // UPDATE by ID
    @PutMapping("/id/{id}")
    public ResponseEntity<Recharge> update(@PathVariable String id, @RequestBody Recharge recharge) {
        return ResponseEntity.ok(service.update(id, recharge));
    }


    // DELETE by ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

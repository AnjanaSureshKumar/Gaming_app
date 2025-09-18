package com.example.GamingApp.services;

import com.example.GamingApp.entities.Transaction;
import com.example.GamingApp.repositories.TransactionRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction create(Transaction transaction) {
        transaction.setId(null);

        if (transaction.getMemberId() == null || transaction.getMemberId().isBlank()) {
            log.error("Transaction creation failed: memberId is required");
            throw new BusinessException("MemberId is required");
        }
        if (transaction.getGameId() == null || transaction.getGameId().isBlank()) {
            log.error("Transaction creation failed: gameId is required");
            throw new BusinessException("GameId is required");
        }
        if (transaction.getAmount() <= 0) {
            log.error("Transaction creation failed: amount must be positive");
            throw new BusinessException("Amount must be positive");
        }
        if (transaction.getDateTime() == null) {
            transaction.setDateTime(new Date()); // auto-fill if missing
        }

        log.info("Creating transaction for memberId: {}, gameId: {}", 
                  transaction.getMemberId(), transaction.getGameId());
        return repo.save(transaction);
    }

    public List<Transaction> findAll() {
        log.info("Fetching all transactions");
        return repo.findAll();
    }

    public Transaction findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Transaction not found: {}", id);
                    return new ResourceNotFoundException("Transaction not found: " + id);
                });
    }

    public Transaction update(String id, Transaction updated) {
        Transaction existing = findById(id);

        if (updated.getAmount() <= 0) {
            throw new BusinessException("Amount must be positive");
        }

        existing.setMemberId(updated.getMemberId());
        existing.setGameId(updated.getGameId());
        existing.setAmount(updated.getAmount());
        existing.setDateTime(updated.getDateTime() != null ? updated.getDateTime() : new Date());

        log.info("Updating transaction: {}", id);
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            log.error("Attempted to delete non-existing transaction: {}", id);
            throw new ResourceNotFoundException("Transaction not found: " + id);
        }
        log.info("Deleting transaction: {}", id);
        repo.deleteById(id);
    }
}

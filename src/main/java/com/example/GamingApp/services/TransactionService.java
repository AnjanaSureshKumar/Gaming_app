package com.example.GamingApp.services;

import com.example.GamingApp.entities.Transaction;
import com.example.GamingApp.repositories.TransactionRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    //private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction create(Transaction transaction) {
        transaction.setId(null);
        if (transaction.getMemberId() == null || transaction.getMemberId().isBlank()) {
            throw new BusinessException("MemberId is required");
        }
        if (transaction.getGameId() == null || transaction.getGameId().isBlank()) {
            throw new BusinessException("GameId is required");
        }
        if (transaction.getAmount() <= 0) {
            throw new BusinessException("Amount must be positive");
        }
        if (transaction.getDateTime() == null) {
            transaction.setDateTime(new Date());
        }
        return repo.save(transaction);
    }

    public List<Transaction> findAll() {
        return repo.findAll();
    }

    public Transaction findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + id));
    }

    public List<Transaction> findByMemberId(String memberId) {
        List<Transaction> list = repo.findByMemberIdContainingIgnoreCase(memberId);
        if (list.isEmpty()) throw new ResourceNotFoundException("No transactions found for memberId: " + memberId);
        return list;
    }

    public List<Transaction> findByGameId(String gameId) {
        List<Transaction> list = repo.findByGameIdContainingIgnoreCase(gameId);
        if (list.isEmpty()) throw new ResourceNotFoundException("No transactions found for gameId: " + gameId);
        return list;
    }

    public List<Transaction> findByAmount(float amount) {
        List<Transaction> list = repo.findByAmount(amount);
        if (list.isEmpty()) throw new ResourceNotFoundException("No transactions found with amount: " + amount);
        return list;
    }

    public List<Transaction> findByDateTime(Date dateTime) {
        List<Transaction> list = repo.findByDateTime(dateTime);
        if (list.isEmpty()) throw new ResourceNotFoundException("No transactions found on date: " + dateTime);
        return list;
    }

    public Transaction update(String id, Transaction updated) {
        Transaction existing = findById(id);
        if (updated.getAmount() <= 0) throw new BusinessException("Amount must be positive");

        existing.setMemberId(updated.getMemberId());
        existing.setGameId(updated.getGameId());
        existing.setAmount(updated.getAmount());
        existing.setDateTime(updated.getDateTime() != null ? updated.getDateTime() : new Date());
        return repo.save(existing);
    }


    public void delete(String id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Transaction not found: " + id);
        repo.deleteById(id);
    }
   
}

package com.example.GamingApp.services;

import com.example.GamingApp.entities.Recharge;
import com.example.GamingApp.repositories.RechargeRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RechargeService {

    private final RechargeRepository repo;
    //private static final Logger log = LoggerFactory.getLogger(RechargeService.class);

    public RechargeService(RechargeRepository repo) {
        this.repo = repo;
    }

    public Recharge create(Recharge recharge) {
        recharge.setId(null);
        if (recharge.getMemberId() == null || recharge.getMemberId().isBlank()) {
            throw new BusinessException("MemberId is required");
        }
        if (recharge.getAmount() <= 0) {
            throw new BusinessException("Recharge amount must be positive");
        }
        return repo.save(recharge);
    }

    public List<Recharge> findAll() {
        return repo.findAll();
    }

    public Recharge findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recharge not found: " + id));
    }

    public List<Recharge> findByMemberId(String memberId) {
        List<Recharge> list = repo.findByMemberIdContainingIgnoreCase(memberId);
        if (list.isEmpty()) throw new ResourceNotFoundException("No recharge found for memberId: " + memberId);
        return list;
    }

    public List<Recharge> findByAmount(float amount) {
        List<Recharge> list = repo.findByAmount(amount);
        if (list.isEmpty()) throw new ResourceNotFoundException("No recharge found with amount: " + amount);
        return list;
    }

    public Recharge update(String id, Recharge updated) {
        Recharge existing = findById(id);
        if (updated.getAmount() <= 0) throw new BusinessException("Recharge amount must be positive");
        existing.setMemberId(updated.getMemberId());
        existing.setAmount(updated.getAmount());
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Recharge not found: " + id);
        repo.deleteById(id);
    }
}

package com.example.GamingApp.services;

import com.example.GamingApp.entities.Recharge;
import com.example.GamingApp.repositories.RechargeRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RechargeService {

    private final RechargeRepository repo;
    private static final Logger log = LoggerFactory.getLogger(RechargeService.class);

    public RechargeService(RechargeRepository repo) {
        this.repo = repo;
    }

    public Recharge create(Recharge recharge) {
        recharge.setId(null);

        if (recharge.getMemberId() == null || recharge.getMemberId().isBlank()) {
            log.error("Recharge creation failed: memberId is required");
            throw new BusinessException("MemberId is required");
        }
        if (recharge.getAmount() <= 0) {
            log.error("Recharge creation failed: amount must be positive");
            throw new BusinessException("Recharge amount must be positive");
        }

        log.info("Creating recharge for memberId: {}", recharge.getMemberId());
        return repo.save(recharge);
    }

    public List<Recharge> findAll() {
        log.info("Fetching all recharges");
        return repo.findAll();
    }

    public Recharge findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Recharge not found: {}", id);
                    return new ResourceNotFoundException("Recharge not found: " + id);
                });
    }

    public Recharge update(String id, Recharge updated) {
        Recharge existing = findById(id);

        if (updated.getAmount() <= 0) {
            throw new BusinessException("Recharge amount must be positive");
        }

        existing.setMemberId(updated.getMemberId());
        existing.setAmount(updated.getAmount());

        log.info("Updating recharge: {}", id);
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            log.error("Attempted to delete non-existing recharge: {}", id);
            throw new ResourceNotFoundException("Recharge not found: " + id);
        }
        log.info("Deleting recharge: {}", id);
        repo.deleteById(id);
    }
}

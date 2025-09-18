package com.example.GamingApp.services;

import com.example.GamingApp.entities.CollectionSummary;
import com.example.GamingApp.repositories.CollectionRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    private final CollectionRepository repo;
    private static final Logger log = LoggerFactory.getLogger(CollectionService.class);

    public CollectionService(CollectionRepository repo) {
        this.repo = repo;
    }

    public CollectionSummary create(CollectionSummary summary) {
        summary.setId(null);
        if (summary.getDate() == null) {   // ✅ only null check needed
            throw new BusinessException("Date is required");
        }
        log.info("Creating collection summary for date {}", summary.getDate());
        return repo.save(summary);
    }


    public List<CollectionSummary> findAll() {
        log.info("Fetching all collection summaries");
        return repo.findAll();
    }

    public CollectionSummary findById(String id) {
        log.info("Fetching collection summary with id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection summary not found: " + id));
    }

    public CollectionSummary update(String id, CollectionSummary updated) {
        CollectionSummary existing = findById(id);
        existing.setDate(updated.getDate());
        existing.setTotalRecharges(updated.getTotalRecharges());
        log.info("Updating collection summary {}", id);
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            log.error("Collection summary not found with id {}", id);
            throw new ResourceNotFoundException("Collection summary not found: " + id);
        }
        log.info("Deleting collection summary {}", id);
        repo.deleteById(id);
    }
}

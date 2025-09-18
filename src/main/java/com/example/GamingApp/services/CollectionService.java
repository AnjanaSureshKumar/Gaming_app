package com.example.GamingApp.services;

import com.example.GamingApp.entities.CollectionSummary;
import com.example.GamingApp.repositories.CollectionRepository;
import com.example.GamingApp.exceptions.BusinessException;
import com.example.GamingApp.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionService {

    private final CollectionRepository repo;
    private static final Logger log = LoggerFactory.getLogger(CollectionService.class);

    public CollectionService(CollectionRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public CollectionSummary create(CollectionSummary summary) {
        summary.setId(null);
        if (summary.getDate() == null) {
            throw new BusinessException("Date is required");
        }
        log.info("Creating collection summary for date {}", summary.getDate());
        return repo.save(summary);
    }

    // READ ALL with optional filters
    public List<CollectionSummary> findAll(Date date, Float totalRecharges) {
        List<CollectionSummary> list = repo.findAll();

        if (date != null) {
            list = list.stream()
                    .filter(c -> c.getDate() != null && c.getDate().equals(date))
                    .collect(Collectors.toList());
        }

        if (totalRecharges != null) {
            list = list.stream()
                    .filter(c -> c.getTotalRecharges() == totalRecharges)
                    .collect(Collectors.toList());
        }

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No collection summaries found for given filters");
        }

        return list;
    }

    // READ BY ID
    public CollectionSummary findById(String id) {
        log.info("Fetching collection summary with id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection summary not found: " + id));
    }

    // UPDATE BY ID
    public CollectionSummary update(String id, CollectionSummary updated) {
        CollectionSummary existing = findById(id);
        if (updated.getDate() != null) existing.setDate(updated.getDate());
        existing.setTotalRecharges(updated.getTotalRecharges());
        log.info("Updating collection summary {}", id);
        return repo.save(existing);
    }

    // DELETE BY ID
    public void delete(String id) {
        if (!repo.existsById(id)) {
            log.error("Collection summary not found with id {}", id);
            throw new ResourceNotFoundException("Collection summary not found: " + id);
        }
        log.info("Deleting collection summary {}", id);
        repo.deleteById(id);
    }
}

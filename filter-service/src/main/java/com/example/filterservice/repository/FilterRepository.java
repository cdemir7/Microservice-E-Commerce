package com.example.filterservice.repository;

import com.example.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, UUID> {
    void deleteByProductId(UUID productId);
    Filter findByProductId(UUID productId);
}

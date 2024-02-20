package com.example.filterservice.business.abstracts;

import com.example.filterservice.business.dto.responses.GetAllFilterResponse;
import com.example.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFilterResponse> getAll();
    void add(Filter filter);
    void delete(UUID  id);
    void deleteByProductId(UUID productId);
    void update(Filter filter);
}

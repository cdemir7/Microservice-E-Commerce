package com.example.filterservice.business.concretes;

import com.example.filterservice.business.abstracts.FilterService;
import com.example.filterservice.business.dto.responses.GetAllFilterResponse;
import com.example.filterservice.entities.Filter;
import com.example.filterservice.repository.FilterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllFilterResponse> getAll() {
        List<Filter> filters = repository.findAll();
        List<GetAllFilterResponse> responses = filters
                .stream()
                .map(filter -> mapper.map(filter, GetAllFilterResponse.class))
                .toList();

        return responses;
    }

    @Override
    public void add(Filter filter) {
        repository.save(filter);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByProductId(UUID productId) {
        repository.deleteByProductId(productId);
    }

    @Override
    public void update(Filter filter) {
        Filter filter1 = repository.findByProductId(filter.getProductId());
        filter.setId(filter1.getId());
        repository.save(filter);
    }

    @Override
    public void calculateQuantityDec(UUID productId, int buyQuantity) {
        Filter filter = repository.findByProductId(productId);
        int newQuantity = filter.getQuantity() - buyQuantity;
        filter.setQuantity(newQuantity);
        repository.save(filter);
    }

    @Override
    public void calculateQuantityInc(UUID productId, int buyQuantity) {
        Filter filter = repository.findByProductId(productId);
        int newQuantity = filter.getQuantity() + buyQuantity;
        filter.setQuantity(newQuantity);
        repository.save(filter);
    }
}

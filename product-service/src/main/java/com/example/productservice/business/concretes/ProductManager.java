package com.example.productservice.business.concretes;

import com.example.commonpackage.configuration.mapper.ModelMapperConfig;
import com.example.productservice.business.abstracts.ProductService;
import com.example.productservice.business.dto.requests.create.CreateProductRequest;
import com.example.productservice.business.dto.requests.update.UpdateProductRequest;
import com.example.productservice.business.dto.responses.create.CreateProductResponse;
import com.example.productservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.productservice.business.dto.responses.get.GetProductResponse;
import com.example.productservice.business.dto.responses.update.UpdateProductResponse;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllProductsResponse> getAll() {
        return null;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        return null;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

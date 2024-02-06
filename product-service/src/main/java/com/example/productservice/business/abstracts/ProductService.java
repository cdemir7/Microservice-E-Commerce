package com.example.productservice.business.abstracts;

import com.example.productservice.business.dto.requests.create.CreateProductRequest;
import com.example.productservice.business.dto.requests.update.UpdateProductRequest;
import com.example.productservice.business.dto.responses.create.CreateProductResponse;
import com.example.productservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.productservice.business.dto.responses.get.GetProductResponse;
import com.example.productservice.business.dto.responses.update.UpdateProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(UUID id);
    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update(UUID id, UpdateProductRequest request);
    void delete(UUID id);
}

package com.example.productservice.api.controllers;

import com.example.productservice.business.abstracts.ProductService;
import com.example.productservice.business.dto.requests.create.CreateProductRequest;
import com.example.productservice.business.dto.requests.update.UpdateProductRequest;
import com.example.productservice.business.dto.responses.create.CreateProductResponse;
import com.example.productservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.productservice.business.dto.responses.get.GetProductResponse;
import com.example.productservice.business.dto.responses.update.UpdateProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    @GetMapping
    List<GetAllProductsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    GetProductResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateProductResponse add(@Valid @RequestBody CreateProductRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    UpdateProductResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateProductRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id){
        service.delete(id);
    }
}

package com.example.productservice.business.rules;

import com.example.commonpackage.utils.constants.Paths;
import com.example.productservice.entities.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;

    public void checkIfCarExists(UUID id){
        if (!repository.existsById(id)){
            throw new RuntimeException("PRODUCT_NOT_EXISTS");
        }
    }

    public void validProductQuantity(UUID id){
        Product product = repository.findById(id).orElseThrow();
        if (product.getQuantity() < 1){
            throw new RuntimeException("OUT_OF_STOCK");
        }
    }
}

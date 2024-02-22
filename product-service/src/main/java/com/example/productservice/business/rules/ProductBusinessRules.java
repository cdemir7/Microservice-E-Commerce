package com.example.productservice.business.rules;

import com.example.commonpackage.utils.dto.CartProductQuantity;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.productservice.entities.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;

    public void checkIfCarExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException("PRODUCT_NOT_EXISTS");
        }
    }

    public void validProductQuantity(UUID id){
        Product product = repository.findById(id).orElseThrow();
        if (product.getQuantity() < 1){
            throw new BusinessException("OUT_OF_STOCK");
        }
    }

    public void checkIfProductBuyQuantity(UUID productId, int buyQuantity){
        Product product = repository.findById(productId).orElseThrow();
        if (product.getQuantity() < buyQuantity){
            throw new BusinessException("NOT_ENOUGH_PRODUCT");
        }
    }
}

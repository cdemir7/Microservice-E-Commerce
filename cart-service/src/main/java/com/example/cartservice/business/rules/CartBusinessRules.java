package com.example.cartservice.business.rules;

import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartBusinessRules {
    private final CartRepository repository;

    public void checkIfBuyQuantity(int buyQuantity){
        if (buyQuantity < 1){
            throw new RuntimeException("NOT_PRODUCT");
        }
    }
}
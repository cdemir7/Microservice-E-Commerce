package com.example.cartservice.business.rules;

import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.utils.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartBusinessRules {
    private final CartRepository repository;

    public void checkIfBuyQuantity(int buyQuantity){
        if (buyQuantity < 1){
            throw new BusinessException("NOT_PRODUCT");
        }
    }
}

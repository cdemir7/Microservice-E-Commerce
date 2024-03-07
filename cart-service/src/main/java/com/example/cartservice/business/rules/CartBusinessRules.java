package com.example.cartservice.business.rules;

import com.example.cartservice.api.clients.CustomerClient;
import com.example.cartservice.api.clients.ProductClient;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartBusinessRules {
    private final CartRepository repository;
    private final ProductClient productClient;
    private final CustomerClient customerClient;

    public void checkIfBuyQuantity(int buyQuantity){
        if (buyQuantity < 1){
            throw new BusinessException("NOT_PRODUCT");
        }
    }

    public void ensureProductQuantity(UUID productId, int buyQuantity){
        ClientResponse response = productClient.checkIfProductBuyQuantity(productId, buyQuantity);
        if (!response.isSuccess()){
            throw new BusinessException(response.getMessage());
        }
    }

    public void checkIfExistsCustomer(UUID customerId){
        ClientResponse response = customerClient.checkIfExistsCustomer(customerId);
        if (!response.isSuccess()){
            throw new BusinessException(response.getMessage());
        }
    }
}

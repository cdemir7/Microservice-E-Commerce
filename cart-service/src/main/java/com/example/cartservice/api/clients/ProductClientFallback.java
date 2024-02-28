package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductClientFallback implements ProductClient{
    @Override
    public ClientResponse checkIfProductBuyQuantity(UUID productId, int buyQuantity) {
        throw new RuntimeException("PRODUCT SERVICE IS DOWN!");
    }
}

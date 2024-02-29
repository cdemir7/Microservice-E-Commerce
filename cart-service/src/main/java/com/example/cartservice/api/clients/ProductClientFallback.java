package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient{
    @Override
    public ClientResponse checkIfProductBuyQuantity(UUID productId, int buyQuantity) {
        log.info("PRODUCT SERVICE IS DOWN!");
        throw new RuntimeException("PRODUCT SERVICE IS DOWN!");
    }
}

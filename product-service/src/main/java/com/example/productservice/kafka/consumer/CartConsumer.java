package com.example.productservice.kafka.consumer;

import com.example.commonpackage.events.cart.CartCreatedEvent;
import com.example.productservice.business.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartConsumer {
    private final ProductService service;

    @KafkaListener(
            topics = "cart-created",
            groupId = "product-cart-create"
    )
    public void consume(CartCreatedEvent event){
        service.calculateQuantity(event.getProductId(), event.getBuyQuantity());
        log.info("Cart created event consume {}", event);
    }
}

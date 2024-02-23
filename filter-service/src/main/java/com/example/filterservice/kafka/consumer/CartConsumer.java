package com.example.filterservice.kafka.consumer;

import com.example.commonpackage.events.cart.CartCreatedEvent;
import com.example.commonpackage.events.cart.CartDeletedEvent;
import com.example.filterservice.business.abstracts.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartConsumer {
    private final FilterService service;

    @KafkaListener(
            topics = "cart-created",
            groupId = "filter-cart-create"
    )
    public void consume(CartCreatedEvent event){
        service.calculateQuantityDec(event.getProductId(), event.getBuyQuantity());
        log.info("Cart created event consume {}", event);
    }

    @KafkaListener(
            topics = "cart-deleted",
            groupId = "filter-cart-create"
    )
    public void consume(CartDeletedEvent event){
        service.calculateQuantityInc(event.getProductId(), event.getBuyQuantity());
        log.info("Cart created event consume {}", event);
    }
}

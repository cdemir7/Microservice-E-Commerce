package com.example.filterservice.kafka.consumer;

import com.example.commonpackage.events.product.ProductCreatedEvent;
import com.example.commonpackage.events.product.ProductDeletedEvent;
import com.example.commonpackage.events.product.ProductUpdatedEvent;
import com.example.filterservice.business.abstracts.FilterService;
import com.example.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductConsumer {
    private final FilterService service;
    private final ModelMapper mapper;

    @KafkaListener(
            topics = "product-created",
            groupId = "product-create"
    )
    public void consume(ProductCreatedEvent event){
        Filter filter = mapper.map(event, Filter.class);
        service.add(filter);
        log.info("Product created event consume {}", event);
    }

    @KafkaListener(
            topics = "product-deleted",
            groupId = "product-delete"
    )
    public void consume(ProductDeletedEvent event){
        service.deleteByProductId(event.getProductId());
        log.info("Product deleted event consume {}", event);
    }

    @KafkaListener(
            topics = "product-updated",
            groupId = "product-update"
    )
    public void consume(ProductUpdatedEvent event){
        Filter filter = mapper.map(event, Filter.class);
        service.update(filter);
        log.info("Product deleted event consume {}", event);
    }
}

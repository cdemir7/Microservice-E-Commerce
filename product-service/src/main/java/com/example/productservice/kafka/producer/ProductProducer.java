package com.example.productservice.kafka.producer;

import com.example.commonpackage.events.product.ProductCreatedEvent;
import com.example.commonpackage.events.product.ProductDeletedEvent;
import com.example.commonpackage.events.product.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(ProductCreatedEvent event){
        log.info(String.format("product-created event => %s", event.toString()));
        Message<ProductCreatedEvent> message = MessageBuilder.
                withPayload(event).
                setHeader(KafkaHeaders.TOPIC, "product-created")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(ProductDeletedEvent event){
        log.info(String.format("product-deleted event => %s", event.toString()));
        Message<ProductDeletedEvent> message = MessageBuilder.
                withPayload(event).
                setHeader(KafkaHeaders.TOPIC, "product-deleted")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(ProductUpdatedEvent event){
        log.info(String.format("product-updated event => %s", event.toString()));
        Message<ProductUpdatedEvent> message = MessageBuilder.
                withPayload(event).
                setHeader(KafkaHeaders.TOPIC, "product-updated")
                .build();

        kafkaTemplate.send(message);
    }
}

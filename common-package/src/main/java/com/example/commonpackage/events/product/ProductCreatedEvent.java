package com.example.commonpackage.events.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    private UUID productId;
    private String name;
    private String categoryName;
    private int quantity;
    private double unitPrice;
}


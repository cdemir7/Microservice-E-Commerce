package com.example.productservice.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
        private UUID id;
        private String name;
        private String categoryName;
        private int quantity;
        private double unitPrice;

}


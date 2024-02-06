package com.example.productservice.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductResponse {
    private UUID id;
    private String name;
    private String categoryName;
    private int quantity;
    private double unitPrice;

}

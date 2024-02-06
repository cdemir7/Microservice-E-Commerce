package com.example.productservice.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsResponse {
    private UUID id;
    private String name;
    private String categoryName;
    private int quantity;
    private double unitPrice;

}

package com.example.filterservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFilterResponse {
    private String id;
    private UUID productId;
    private String name;
    private String categoryName;
    private int quantity;
    private double unitPrice;
}

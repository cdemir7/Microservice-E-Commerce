package com.example.cartservice.business.dto.responses.update;

import com.example.cartservice.entities.BuyProducts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartResponse {
    private UUID id;
    private UUID customerId;
    private UUID productId;
    private int buyQuantity;
}

package com.example.cartservice.business.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequest {
    private UUID productId;
    private UUID customerId;
    private int buyQuantity;
}


package com.example.orderservice.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrdersResponse {
    private UUID id;
    private UUID productId;
    private UUID customerId;
    private int buyQuantity;
    private LocalDateTime orderTime;
}

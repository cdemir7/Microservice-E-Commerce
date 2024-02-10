package com.example.orderservice.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    @NotBlank
    private UUID productId;

    @NotBlank
    private UUID customerId;

    @NotBlank
    @Min(1)
    private int buyQuantity;
}

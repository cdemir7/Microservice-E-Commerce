package com.example.orderservice.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private UUID productId;

    @NotNull
    private UUID customerId;

    @NotNull
    @Min(1)
    private int buyQuantity;
}

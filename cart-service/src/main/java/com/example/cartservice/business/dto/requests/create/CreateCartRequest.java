package com.example.cartservice.business.dto.requests.create;

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
public class CreateCartRequest {
    @NotBlank
    private UUID productId;

    @NotBlank
    private UUID customerId;

    @NotNull
    @Min(1)
    private int buyQuantity;
}


package com.example.cartservice.business.dto.responses.create;

import com.example.cartservice.entities.BuyProducts;
import jakarta.validation.constraints.NotNull;
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
public class CreateCartResponse {
    private UUID id;
    private UUID customerId;
    private List<BuyProducts> buyProducts;
}



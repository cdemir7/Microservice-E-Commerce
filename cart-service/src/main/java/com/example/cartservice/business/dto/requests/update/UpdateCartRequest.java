package com.example.cartservice.business.dto.requests.update;

import com.example.cartservice.entities.BuyProducts;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class UpdateCartRequest {
    @NotNull
    private UUID customerId;

    private List<BuyProducts> buyProducts;
}

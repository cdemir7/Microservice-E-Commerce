package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping(value = "/api/products/check-product-buy-quantity/")
    ClientResponse checkIfProductBuyQuantity(@RequestParam UUID productId, @RequestParam int buyQuantity);
}

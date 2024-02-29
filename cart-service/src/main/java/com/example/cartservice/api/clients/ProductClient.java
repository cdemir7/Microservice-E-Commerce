package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "product", fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping(value = "/api/products/check-product-buy-quantity/")
    @Retry(name = "tryAgain")
    ClientResponse checkIfProductBuyQuantity(@RequestParam UUID productId, @RequestParam int buyQuantity);
}

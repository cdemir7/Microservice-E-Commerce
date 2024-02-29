package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customer", fallback = CustomerClientFallback.class)
public interface CustomerClient {
    @GetMapping(value = "/api/customers/check-if-exists-customer/{id}")
    @Retry(name = "tryAgain")
    ClientResponse checkIfExistsCustomer(@PathVariable UUID id);
}

package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerClientFallback implements CustomerClient{
    @Override
    public ClientResponse checkIfExistsCustomer(UUID id) {
        throw new RuntimeException("CUSTOMER SERVICE IS DOWN");
    }
}

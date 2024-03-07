package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component("customerClient")
public class CustomerClientFallback implements CustomerClient{
    @Override
    public ClientResponse checkIfExistsCustomer(UUID id) {
        log.info("CUSTOMER SERVICE IS DOWN!");
        throw new RuntimeException("CUSTOMER SERVICE IS DOWN");
    }
}

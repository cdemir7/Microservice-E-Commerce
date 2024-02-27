package com.example.customerservice.business.rules;

import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository repository;

    public void checkIfExistsCustomer(UUID customerId){
        if (!repository.existsById(customerId)){
            throw new BusinessException("CUSTOMER_NOT_EXISTS");
        }
    }
}

package com.example.customerservice.business.abstracts;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.customerservice.business.dto.requests.create.CreateCustomerRequest;
import com.example.customerservice.business.dto.requests.update.UpdateCustomerRequest;
import com.example.customerservice.business.dto.responses.create.CreateCustomerResponse;
import com.example.customerservice.business.dto.responses.get.GetAllCustomersResponse;
import com.example.customerservice.business.dto.responses.get.GetCustomerResponse;
import com.example.customerservice.business.dto.responses.update.UpdateCustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<GetAllCustomersResponse> getAll();
    GetCustomerResponse getById(UUID id);
    CreateCustomerResponse add(CreateCustomerRequest request);
    UpdateCustomerResponse update(UUID id, UpdateCustomerRequest request);
    void delete(UUID id);
    ClientResponse checkIfExistsCustomer(UUID customerId);
}

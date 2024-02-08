package com.example.customerservice.business.concretes;

import com.example.customerservice.business.abstracts.CustomerService;
import com.example.customerservice.business.dto.requests.create.CreateCustomerRequest;
import com.example.customerservice.business.dto.requests.update.UpdateCustomerRequest;
import com.example.customerservice.business.dto.responses.create.CreateCustomerResponse;
import com.example.customerservice.business.dto.responses.get.GetAllCustomersResponse;
import com.example.customerservice.business.dto.responses.get.GetCustomerResponse;
import com.example.customerservice.business.dto.responses.update.UpdateCustomerResponse;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCustomersResponse> getAll() {
        List<Customer> customers = repository.findAll();
        List<GetAllCustomersResponse> responses = customers
                .stream()
                .map(customer -> mapper.map(customer, GetAllCustomersResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetCustomerResponse getById(UUID id) {
        Customer customer = repository.findById(id).orElseThrow();
        GetCustomerResponse response = mapper.map(customer, GetCustomerResponse.class);

        return response;
    }

    @Override
    public CreateCustomerResponse add(CreateCustomerRequest request) {
        Customer customer = mapper.map(request, Customer.class);
        customer.setId(UUID.randomUUID());
        Customer createdCustomer = repository.save(customer);
        CreateCustomerResponse response = mapper.map(createdCustomer, CreateCustomerResponse.class);

        return response;
    }

    @Override
    public UpdateCustomerResponse update(UUID id, UpdateCustomerRequest request) {
        Customer customer = mapper.map(request, Customer.class);
        customer.setId(id);
        Customer updatedCustomer = repository.save(customer);
        UpdateCustomerResponse response = mapper.map(updatedCustomer, UpdateCustomerResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

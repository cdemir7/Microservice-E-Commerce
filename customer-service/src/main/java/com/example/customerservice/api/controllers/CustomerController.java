package com.example.customerservice.api.controllers;

import com.example.customerservice.business.abstracts.CustomerService;
import com.example.customerservice.business.dto.requests.create.CreateCustomerRequest;
import com.example.customerservice.business.dto.requests.update.UpdateCustomerRequest;
import com.example.customerservice.business.dto.responses.create.CreateCustomerResponse;
import com.example.customerservice.business.dto.responses.get.GetAllCustomersResponse;
import com.example.customerservice.business.dto.responses.get.GetCustomerResponse;
import com.example.customerservice.business.dto.responses.update.UpdateCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    List<GetAllCustomersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    GetCustomerResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateCustomerResponse add(@Valid @RequestBody CreateCustomerRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    UpdateCustomerResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCustomerRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id){
        service.delete(id);
    }

}

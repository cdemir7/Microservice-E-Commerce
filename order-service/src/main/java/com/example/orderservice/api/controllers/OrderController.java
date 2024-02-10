package com.example.orderservice.api.controllers;

import com.example.orderservice.business.abstracts.OrderService;
import com.example.orderservice.business.dto.requests.create.CreateOrderRequest;
import com.example.orderservice.business.dto.responses.create.CreateOrderResponse;
import com.example.orderservice.business.dto.responses.get.GetAllOrdersResponse;
import com.example.orderservice.business.dto.responses.get.GetOrderResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    @GetMapping
    List<GetAllOrdersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    GetOrderResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse add(@Valid @RequestBody CreateOrderRequest request){
        return service.add(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id){
        service.delete(id);
    }
}

package com.example.orderservice.business.abstracts;

import com.example.orderservice.business.dto.requests.create.CreateOrderRequest;
import com.example.orderservice.business.dto.responses.create.CreateOrderResponse;
import com.example.orderservice.business.dto.responses.get.GetAllOrdersResponse;
import com.example.orderservice.business.dto.responses.get.GetOrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<GetAllOrdersResponse> getAll();
    GetOrderResponse getById(UUID id);
    CreateOrderResponse add(CreateOrderRequest request);
    void delete(UUID id);
}

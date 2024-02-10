package com.example.orderservice.business.concretes;

import com.example.orderservice.business.abstracts.OrderService;
import com.example.orderservice.business.dto.requests.create.CreateOrderRequest;
import com.example.orderservice.business.dto.responses.create.CreateOrderResponse;
import com.example.orderservice.business.dto.responses.get.GetAllOrdersResponse;
import com.example.orderservice.business.dto.responses.get.GetOrderResponse;
import com.example.orderservice.entities.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllOrdersResponse> getAll() {
        List<Order> orders = repository.findAll();
        List<GetAllOrdersResponse> responses = orders
                .stream()
                .map(order -> mapper.map(order, GetAllOrdersResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetOrderResponse getById(UUID id) {
        Order order = repository.findById(id).orElseThrow();
        GetOrderResponse response = mapper.map(order, GetOrderResponse.class);

        return response;
    }

    @Override
    public CreateOrderResponse add(CreateOrderRequest request) {
        Order order = mapper.map(request, Order.class);
        order.setId(UUID.randomUUID());
        order.setOrderTime(LocalDateTime.now());
        Order createdOrder = repository.save(order);
        CreateOrderResponse response = mapper.map(createdOrder, CreateOrderResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

package com.example.cartservice.business.concretes;

import com.example.cartservice.business.abstracts.CartService;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.requests.update.UpdateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.dto.responses.update.UpdateCartResponse;
import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    private final CartRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCartsResponse> getAll() {
        List<Cart> carts = repository.findAll();
        List<GetAllCartsResponse> responses = carts
                .stream()
                .map(cart -> mapper.map(cart, GetAllCartsResponse.class))
                .toList();

        return responses;
    }

    @Override
    public CreateCartResponse add(CreateCartRequest request) {
        Cart cart = mapper.map(request, Cart.class);
        cart.setId(UUID.randomUUID());
        Cart createdCart = repository.save(cart);
        CreateCartResponse response = mapper.map(createdCart, CreateCartResponse.class);

        return response;
    }

    @Override
    public UpdateCartResponse update(UUID id, UpdateCartRequest request) {
        Cart cart = mapper.map(request, Cart.class);
        cart.setId(id);
        Cart updatedCart = repository.save(cart);
        UpdateCartResponse response = mapper.map(updatedCart, UpdateCartResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

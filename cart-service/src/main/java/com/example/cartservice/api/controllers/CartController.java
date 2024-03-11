package com.example.cartservice.api.controllers;

import com.example.cartservice.business.abstracts.CartService;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.requests.update.UpdateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.dto.responses.update.UpdateCartResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/carts")
public class CartController {
    private final CartService service;

    @GetMapping
    List<GetAllCartsResponse> getAll(){
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateCartResponse add(@Valid @RequestBody CreateCartRequest request){
        return service.add(request);
    }

    @PostMapping("/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    void createCart(@PathVariable UUID customerId){
        service.createCart(customerId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id){
        service.delete(id);
    }
}

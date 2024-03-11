package com.example.cartservice.repository;

import com.example.cartservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findByCustomerId(UUID customerId);
    Cart deleteByCustomerId(UUID customerID);
}

package com.example.cartservice.repository;

import com.example.cartservice.entities.BuyProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuyProductsRepository extends JpaRepository<BuyProducts, UUID> {
    void deleteByProductIdAndBuyQuantity(UUID productId, int buyQuantity);
}

package com.example.cartservice.business.concretes;

import com.example.cartservice.api.clients.ProductClient;
import com.example.cartservice.business.abstracts.CartService;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.requests.update.UpdateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.dto.responses.update.UpdateCartResponse;
import com.example.cartservice.business.rules.CartBusinessRules;
import com.example.cartservice.entities.BuyProducts;
import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.events.cart.CartCreatedEvent;
import com.example.commonpackage.events.cart.CartDeletedEvent;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
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
    private final CartBusinessRules rules;
    private final KafkaProducer producer;

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
        //rules.checkIfExistsCustomer(request.getCustomerId());
        //rules.checkIfExistsCustomer(request.getCustomerId());
        //rules.checkIfBuyQuantity(request.getBuyProducts());
        //rules.ensureProductQuantity(request.getProductId(), request.getBuyQuantity());
        Cart cart = new Cart();

        cart.setId(UUID.randomUUID());
        cart.setCustomerId(request.getCustomerId());
        cart.setBuyProducts(request.getBuyProducts());
        Cart createdCart = repository.save(cart);
        //sendKafkaCartCreatedEvent(buyProducts);
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
        Cart cart = repository.findById(id).orElseThrow();
        BuyProducts buyProducts = cart.getBuyProducts().get(0);
        sendKafkaCartDeletedEvent(buyProducts);
        repository.deleteById(id);
    }

    //
    private void sendKafkaCartCreatedEvent(BuyProducts buyProducts) {
        producer.sendMessage(new CartCreatedEvent(buyProducts.getProductId(), buyProducts.getBuyQuantity()), "cart-created");
    }

    private void sendKafkaCartDeletedEvent(BuyProducts buyProducts) {
        producer.sendMessage(new CartDeletedEvent(buyProducts.getProductId(), buyProducts.getBuyQuantity()), "cart-deleted");
    }
}

package com.example.cartservice.business.concretes;

import com.example.cartservice.business.abstracts.CartService;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.rules.CartBusinessRules;
import com.example.cartservice.entities.BuyProducts;
import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.BuyProductsRepository;
import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    private final CartRepository cartRepository;
    private final BuyProductsRepository buyProductsRepository;
    private final ModelMapper mapper;
    private final CartBusinessRules rules;
    private final KafkaProducer producer;

    @Override
    public List<GetAllCartsResponse> getAll() {
        List<Cart> carts = cartRepository.findAll();
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

        Cart cart = cartRepository.findByCustomerId(request.getCustomerId());
        cart.setCustomerId(request.getCustomerId());


        List<BuyProducts> buyProductsList = new ArrayList<>();
        BuyProducts buyProducts = new BuyProducts(UUID.randomUUID(),request.getProductId(), request.getBuyQuantity(),cart);
        buyProductsList.add(buyProducts);
        buyProductsRepository.save(buyProducts);



        Cart createdCart = cartRepository.save(cart);
        //sendKafkaCartCreatedEvent(buyProducts);
        CreateCartResponse response = new CreateCartResponse();
        response.setId(createdCart.getId());
        response.setCustomerId(createdCart.getCustomerId());
        response.setBuyQuantity(buyProducts.getBuyQuantity());
        response.setProductId(buyProducts.getProductId());

        return response;
    }

    @Override
    public void delete(UUID id) {
        Cart cart = cartRepository.findByCustomerId(id);
        List<BuyProducts> buyProductsList = buyProductsRepository.findAll();
        //sendKafkaCartDeletedEvent(buyProducts);

    }

    @Override
    public void createCart(UUID customerId) {
        Cart cart = new Cart();
        cart.setId(UUID.randomUUID());
        cart.setCustomerId(customerId);
        cartRepository.save(cart);
    }

    //
    private void sendKafkaCartCreatedEvent(BuyProducts buyProducts) {
        //producer.sendMessage(new CartCreatedEvent(buyProducts.getProductId(), buyProducts.getBuyQuantity()), "cart-created");
    }

    private void sendKafkaCartDeletedEvent(BuyProducts buyProducts) {
        //producer.sendMessage(new CartDeletedEvent(buyProducts.getProductId(), buyProducts.getBuyQuantity()), "cart-deleted");
    }
}

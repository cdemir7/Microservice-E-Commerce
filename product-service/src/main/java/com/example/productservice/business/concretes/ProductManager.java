package com.example.productservice.business.concretes;

import com.example.commonpackage.configuration.mapper.ModelMapperConfig;
import com.example.commonpackage.events.product.ProductCreatedEvent;
import com.example.commonpackage.events.product.ProductDeletedEvent;
import com.example.commonpackage.events.product.ProductUpdatedEvent;
import com.example.commonpackage.utils.dto.CartProductQuantity;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import com.example.productservice.business.abstracts.ProductService;
import com.example.productservice.business.dto.requests.create.CreateProductRequest;
import com.example.productservice.business.dto.requests.update.UpdateProductRequest;
import com.example.productservice.business.dto.responses.create.CreateProductResponse;
import com.example.productservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.productservice.business.dto.responses.get.GetProductResponse;
import com.example.productservice.business.dto.responses.update.UpdateProductResponse;
import com.example.productservice.business.rules.ProductBusinessRules;
import com.example.productservice.entities.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.event.CaretEvent;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;
    private final ProductBusinessRules rules;
    private final KafkaProducer producer;

    @Override
    public List<GetAllProductsResponse> getAll() {
        List<Product> products = repository.findAll();
        List<GetAllProductsResponse> response = products
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        rules.checkIfCarExists(id);
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response = mapper.map(product, GetProductResponse.class);

        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        product.setId(UUID.randomUUID());
        Product createdProduct = repository.save(product);

        sendKafkaProductCreatedEvent(createdProduct);

        CreateProductResponse response = mapper.map(createdProduct, CreateProductResponse.class);

        return response;
    }



    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        rules.checkIfCarExists(id);
        Product product = mapper.map(request, Product.class);
        product.setId(id);
        Product updatedProduct = repository.save(product);

        sendKafkaProductUpdatedEvent(id, updatedProduct);

        UpdateProductResponse response = mapper.map(updatedProduct, UpdateProductResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);
        sendKafkaProductDeletedEvent(id);
    }

    @Override
    public void checkIfProductBuyQuantity(UUID productId, int buyQuantity) {
        rules.checkIfCarExists(productId);
        rules.checkIfProductBuyQuantity(productId,buyQuantity);
    }

    @Override
    public void calculateQuantityDec(UUID productId, int buyQuantity) {
        Product product = repository.findById(productId).orElseThrow();
        int newQuantity = product.getQuantity() - buyQuantity;
        product.setQuantity(newQuantity);
        repository.save(product);
    }

    @Override
    public void calculateQuantityInc(UUID productId, int buyQuantity) {
        Product product = repository.findById(productId).orElseThrow();
        int newQuantity = product.getQuantity() + buyQuantity;
        product.setQuantity(newQuantity);
        repository.save(product);
    }


    //
    private void sendKafkaProductCreatedEvent(Product createdProduct) {
        ProductCreatedEvent event = mapper.map(createdProduct, ProductCreatedEvent.class);
        producer.sendMessage(event, "product-created");
    }

    private void sendKafkaProductUpdatedEvent(UUID id, Product updatedProduct) {
        ProductUpdatedEvent event = mapper.map(updatedProduct, ProductUpdatedEvent.class);
        event.setProductId(id);
        producer.sendMessage(event,"product-updated");
    }

    private void sendKafkaProductDeletedEvent(UUID id) {
        producer.sendMessage(new ProductDeletedEvent(id), "product-deleted");
    }
}

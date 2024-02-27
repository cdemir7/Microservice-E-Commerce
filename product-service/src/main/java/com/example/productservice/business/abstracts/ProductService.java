package com.example.productservice.business.abstracts;

import com.example.commonpackage.utils.dto.CartProductQuantity;
import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.productservice.business.dto.requests.create.CreateProductRequest;
import com.example.productservice.business.dto.requests.update.UpdateProductRequest;
import com.example.productservice.business.dto.responses.create.CreateProductResponse;
import com.example.productservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.productservice.business.dto.responses.get.GetProductResponse;
import com.example.productservice.business.dto.responses.update.UpdateProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(UUID id);
    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update(UUID id, UpdateProductRequest request);
    void delete(UUID id);
    ClientResponse checkIfProductBuyQuantity(UUID productId, int buyQuantity);
    void calculateQuantityInc(UUID productId, int buyQuantity);
    void calculateQuantityDec(UUID productId, int buyQuantity);
}

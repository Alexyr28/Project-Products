package com.alexyr.product.controller;

import com.alexyr.product.dto.CreatePriceHistoryRequest;
import com.alexyr.product.dto.PriceHistoryResponse;
import com.alexyr.product.entity.PriceHistory;
import com.alexyr.product.entity.Product;
import com.alexyr.product.exception.ResourceNotFoundException;
import com.alexyr.product.repository.PriceHistoryRepository;
import com.alexyr.product.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/products/{productId}/price-histories")
public class PriceHistoryController {
    private final ProductRepository productRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistoryController(ProductRepository productRepository,
            PriceHistoryRepository priceHistoryRepository) {
        this.productRepository = productRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriceHistoryResponse create(@PathVariable Long productId,
            @Valid @RequestBody CreatePriceHistoryRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product " + productId + " not found"));

        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setPrice(request.getPrice());
        priceHistory.setRecordedAt(LocalDateTime.now());
        priceHistory.setProduct(product);

        PriceHistory saved = priceHistoryRepository.save(priceHistory);

        PriceHistoryResponse response = new PriceHistoryResponse();
        response.setId(saved.getId());
        response.setPrice(saved.getPrice());
        response.setRecordedAt(saved.getRecordedAt());
        response.setProductId(saved.getProduct().getId());
        return response;
    }
}

package com.alexyr.product.controller;

import com.alexyr.product.dto.CreateSpecificationRequest;
import com.alexyr.product.dto.SpecificationResponse;
import com.alexyr.product.entity.Product;
import com.alexyr.product.entity.Specification;
import com.alexyr.product.exception.ResourceNotFoundException;
import com.alexyr.product.repository.ProductRepository;
import com.alexyr.product.repository.SpecificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/{productId}/specification")
public class SpecificationController {
    private final ProductRepository productRepository;
    private final SpecificationRepository specificationRepository;

    public SpecificationController(ProductRepository productRepository,
            SpecificationRepository specificationRepository) {
        this.productRepository = productRepository;
        this.specificationRepository = specificationRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpecificationResponse create(@PathVariable Long productId,
            @RequestBody CreateSpecificationRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product " + productId + " not found"));

        Specification specification = new Specification();
        specification.setProcessor(request.getProcessor());
        specification.setRam(request.getRam());
        specification.setStorage(request.getStorage());
        specification.setScreenSize(request.getScreenSize());
        specification.setProduct(product);

        Specification saved = specificationRepository.save(specification);

        SpecificationResponse response = new SpecificationResponse();
        response.setId(saved.getId());
        response.setProcessor(saved.getProcessor());
        response.setRam(saved.getRam());
        response.setStorage(saved.getStorage());
        response.setScreenSize(saved.getScreenSize());
        response.setProductId(saved.getProduct().getId());
        return response;
    }
}

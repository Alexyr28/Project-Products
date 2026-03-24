package com.alexyr.product.controller;

import com.alexyr.product.dto.ProductTagResponse;
import com.alexyr.product.service.ProductTagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/{productId}/tags")
public class ProductTagController {

    private final ProductTagService productTagService;

    public ProductTagController(ProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    @PostMapping("/{tagId}")
    public ProductTagResponse assignTag(@PathVariable Long productId,
            @PathVariable Long tagId) {
        return productTagService.assignTag(productId, tagId);
    }

    @DeleteMapping("/{tagId}")
    public ProductTagResponse removeTag(@PathVariable Long productId,
            @PathVariable Long tagId) {
        return productTagService.removeTag(productId, tagId);
    }
}
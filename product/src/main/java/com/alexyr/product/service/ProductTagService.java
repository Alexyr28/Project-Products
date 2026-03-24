package com.alexyr.product.service;

import com.alexyr.product.dto.ProductTagResponse;

public interface ProductTagService {
    ProductTagResponse assignTag(Long productId, Long tagId);

    ProductTagResponse removeTag(Long productId, Long tagId);
}

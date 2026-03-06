package com.alexyr.product.service;

import com.alexyr.product.dto.*;
import java.util.List;

public interface ProductService {
    ProductResponse create(CreateProductRequest request);

    List<ProductResponse> list();
    
    ProductResponse getById(Long id);

    ProductResponse update(Long id, UpdateProductRequest request);

    void delete(Long id);

    ProductResponse markDone(Long id);
}
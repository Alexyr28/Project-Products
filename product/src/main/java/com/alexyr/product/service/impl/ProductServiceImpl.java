package com.alexyr.product.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexyr.product.dto.CreateProductRequest;
import com.alexyr.product.dto.ProductResponse;
import com.alexyr.product.dto.UpdateProductRequest;
import com.alexyr.product.entity.Product;
import com.alexyr.product.exception.ResourceNotFoundException;
import com.alexyr.product.repository.ProductRepository;
import com.alexyr.product.service.ProductService;
import java.time.Instant;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public ProductResponse create(CreateProductRequest request){
        Product a = new Product();
        a.setName(request.getName());
        a.setDescription(request.getDescription());
        a.setPrice(request.getPrice());
        a.setStock(request.getStock());
        Product saved = repository.save(a);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> list(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id){
        Product a = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product "+ id + " not found"));
        return toResponse(a);
    }

    @Override
    public ProductResponse update(Long id, UpdateProductRequest request){
        Product a = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product "+ id + " not found"));
        a.setName(request.getName());
        a.setDescription(request.getDescription());
        a.setPrice(request.getPrice());
        a.setStock(request.getStock());
        return toResponse(repository.save(a));
    }

    @Override
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product "+ id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public ProductResponse markDone(Long id){
        Product a = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product "+ id + " not found"));
        a.setCompletedAt(Instant.now());
        return toResponse(repository.save(a));
    }

    private ProductResponse toResponse(Product a){
        ProductResponse r = new ProductResponse();
        r.setId(a.getId());
        r.setName(a.getName());
        r.setDescription(a.getDescription());
        r.setPrice(a.getPrice());
        r.setStock(a.getStock());
        r.setCreatedAt(a.getCreatedAt());
        r.setUpdatedAt(a.getUpdatedAt());
        return r;
    }
}

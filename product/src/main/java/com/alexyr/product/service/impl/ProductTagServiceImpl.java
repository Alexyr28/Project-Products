package com.alexyr.product.service.impl;

import com.alexyr.product.dto.ProductTagResponse;
import com.alexyr.product.dto.TagResponse;
import com.alexyr.product.entity.Product;
import com.alexyr.product.entity.Tag;
import com.alexyr.product.exception.ResourceNotFoundException;
import com.alexyr.product.repository.ProductRepository;
import com.alexyr.product.repository.TagRepository;
import com.alexyr.product.service.ProductTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductTagServiceImpl implements ProductTagService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    public ProductTagServiceImpl(ProductRepository productRepository,
            TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ProductTagResponse assignTag(Long productId, Long tagId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product " + productId + " not found"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag " + tagId + " not found"));

        product.getTags().add(tag);
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Override
    public ProductTagResponse removeTag(Long productId, Long tagId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product " + productId + " not found"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag " + tagId + " not found"));

        product.getTags().remove(tag);
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    private ProductTagResponse toResponse(Product product) {
        ProductTagResponse response = new ProductTagResponse();
        response.setProductId(product.getId());
        response.setName(product.getName());

        List<TagResponse> tags = product.getTags().stream().map(tag -> {
            TagResponse tr = new TagResponse();
            tr.setId(tag.getId());
            tr.setName(tag.getName());
            return tr;
        }).toList();

        response.setTags(tags);
        return response;
    }
}

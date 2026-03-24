package com.alexyr.product.controller;

import com.alexyr.product.dto.CreateTagRequest;
import com.alexyr.product.dto.TagResponse;
import com.alexyr.product.entity.Tag;
import com.alexyr.product.repository.TagRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagRepository repository;

    public TagController(TagRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TagResponse> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponse create(@Valid @RequestBody CreateTagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        Tag saved = repository.save(tag);
        return toResponse(saved);
    }

    private TagResponse toResponse(Tag tag) {
        TagResponse response = new TagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());
        return response;
    }
}
package com.alexyr.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateProductRequest {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "title must be <= 100 chars")
    private String name;

    @Size(max = 2000, message = "description must be <= 2000 chars")
    private String description;

    @NotNull(message = "price is required")
    private Double price;

    @NotNull(message = "stock is required")
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
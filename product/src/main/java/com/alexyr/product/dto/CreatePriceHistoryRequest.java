package com.alexyr.product.dto;

import jakarta.validation.constraints.NotNull;

public class CreatePriceHistoryRequest {
    @NotNull(message = "price is required")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

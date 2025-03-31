package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class CreateCartItemDto {
    private Integer productId;
    @Positive
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}

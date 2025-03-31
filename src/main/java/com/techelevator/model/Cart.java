package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;

   private BigDecimal productsTotal;

   private BigDecimal taxAmount;

   private BigDecimal cartTotal;

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getProductsTotal() {
        return productsTotal;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public void setProductsTotal(BigDecimal productsTotal) {
        this.productsTotal = productsTotal;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

}

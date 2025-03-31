package com.techelevator.dao;

import com.techelevator.model.CartItem;
import com.techelevator.model.CreateCartItemDto;

import java.security.Principal;
import java.util.List;

public interface CartItemDao {
    public List<CartItem> getCartItemsByUserId(Integer userId);

    public CartItem addCartItem(CreateCartItemDto createCartItemDto, Integer userId);

    public int deleteCartItemByProductIdAndUserId(Integer productId, Integer userId);

    public int deleteCartItemByUserId(Integer userId);

}

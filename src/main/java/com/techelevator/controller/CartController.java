package com.techelevator.controller;

import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import com.techelevator.model.CreateCartItemDto;
import com.techelevator.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public Cart getCart(Principal principal){
        Cart cart = cartService.getCart(principal);
        return cart;
    }

    @RequestMapping(path = "/items",method = RequestMethod.POST)
    public CartItem addNewCartItem(@RequestBody @Valid CreateCartItemDto createCartItemDto, Principal principal){
        CartItem cartItem = cartService.addNewCartItem(createCartItemDto, principal);
        return cartItem;
    }

    @RequestMapping(path = "/items/{id}",method = RequestMethod.DELETE)
    public Integer deleteCartItem(@PathVariable Integer id,Principal principal){
        Integer count = cartService.deleteByProductIdAndUserId(id,principal);
        return count;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Integer deleteCart(Principal principal){
        Integer count = cartService.deleteByUserId(principal);
        return count;
    }
}

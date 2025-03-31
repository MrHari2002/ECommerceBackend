package com.techelevator.service;

import com.techelevator.dao.CartItemDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import com.techelevator.model.CreateCartItemDto;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;

@Component
public class CartService {

    private CartItemDao cartItemDao;
    private UserDao userDao;

    private TaxService taxService;

    public CartService(CartItemDao cartItemDao, UserDao userDao, TaxService taxService){
        this.cartItemDao = cartItemDao;
        this.userDao = userDao;
        this.taxService = taxService;
    }

    public List<CartItem> getCartItems(Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        return cartItemDao.getCartItemsByUserId(user.getId());
    }

    public Cart getCart(Principal principal){
        Cart cart = new Cart();
        User user = userDao.getUserByUsername(principal.getName());
        List<CartItem> cartItems = getCartItems(principal);
        double productTotal = getProductsTotal(cartItems);
        BigDecimal taxRate = taxService.getTaxRate(user.getStateCode());
        BigDecimal bigDecimalProductTotal = new BigDecimal(productTotal);
        BigDecimal taxAmount = bigDecimalProductTotal.multiply(taxRate);
        BigDecimal cartTotal = taxAmount.add(bigDecimalProductTotal);
        cart.setCartItemList(cartItems);
        cart.setCartTotal(cartTotal.setScale(2, RoundingMode.DOWN));
        cart.setProductsTotal(bigDecimalProductTotal.setScale(2,RoundingMode.DOWN));
        cart.setTaxAmount(taxAmount.setScale(2, RoundingMode.DOWN));
        return cart;
    }

    public CartItem addNewCartItem(CreateCartItemDto createCartItemDto, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        CartItem cartItem = cartItemDao.addCartItem(createCartItemDto, user.getId());
        return cartItem;
    }

    public Integer deleteByProductIdAndUserId(Integer productId, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        Integer count = cartItemDao.deleteCartItemByProductIdAndUserId(productId,user.getId());
        return count;
    }

    public Integer deleteByUserId(Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        Integer count = cartItemDao.deleteCartItemByUserId(user.getId());
        return count;
    }
    private double getProductsTotal(List<CartItem> cartItems){
        double total = 0;
        for(CartItem cartItem:cartItems){
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return total;
    }



}

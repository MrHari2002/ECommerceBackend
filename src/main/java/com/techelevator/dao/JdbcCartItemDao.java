package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<CartItem> getCartItemsByUserId(Integer userId) {
        String sql = "SELECT * FROM cart_item c JOIN product p on c.product_id = p.product_id WHERE user_id = ? ORDER BY cart_item_id";
        List<CartItem> cartItemList = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        while(results.next()){
            cartItemList.add(mapRowToCartItem(results));
        }
        return cartItemList;
    }

    @Override
    public CartItem addCartItem(CreateCartItemDto createCartItemDto, Integer userId) {
        CartItem newCartItem = null;
        List<CartItem> cartItemList = getCartItemsByUserId(userId);

        for(CartItem cartItem:cartItemList){
            if(cartItem.getProductId() == createCartItemDto.getProductId()){
                cartItem.setQuantity(cartItem.getQuantity()+createCartItemDto.getQuantity());
                String sql = "Update cart_item SET quantity = ? WHERE cart_item_id = ?";
                jdbcTemplate.update(sql,cartItem.getQuantity(),cartItem.getCartItemId());
                return getCartItemById(cartItem.getCartItemId());
            }
        }

        String sql = "INSERT INTO cart_item(user_id, product_id,quantity) VALUES (?,?,?) RETURNING cart_item_id";
        int newCartItemId = jdbcTemplate.queryForObject(sql,int.class,userId,createCartItemDto.getProductId(),createCartItemDto.getQuantity());
        newCartItem = getCartItemById(newCartItemId);
        return newCartItem;
    }

    @Override
    public int deleteCartItemByProductIdAndUserId(Integer productId, Integer userId) {
        String sql = "DELETE FROM cart_item WHERE product_id = ? AND user_id = ?";
        int count = jdbcTemplate.update(sql,productId,userId);
        return count;
    }

    @Override
    public int deleteCartItemByUserId(Integer userId) {
        String sql = "DELETE FROM cart_item WHERE user_id = ?";
        int count = jdbcTemplate.update(sql,userId);
        return count;
    }

    private CartItem getCartItemById(Integer cartItemId){
        CartItem cartItem = null;
        String sql = "SELECT * FROM cart_item c JOIN product p on c.product_id = p.product_id WHERE cart_item_id = ? ORDER BY cart_item_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,cartItemId);
        if(results.next()){
            cartItem = mapRowToCartItem(results);
        }
        return cartItem;
    }

    private CartItem mapRowToCartItem(SqlRowSet rs){
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(rs.getInt("cart_item_id"));
        cartItem.setUserId(rs.getInt("user_id"));
        cartItem.setProductId(rs.getInt("product_id"));
        cartItem.setQuantity(rs.getInt("quantity"));
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setProduct_sku(rs.getString("product_sku"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setImage_name(rs.getString("image_name"));
        cartItem.setProduct(product);
        return cartItem;
    }
}

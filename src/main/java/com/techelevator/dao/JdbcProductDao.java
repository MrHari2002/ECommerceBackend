package com.techelevator.dao;

import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductsWithSKU(String sku) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_sku = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sku);
        while(results.next()){
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductsWithName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        while(results.next()){
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductWithId(Integer id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE  product_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            product = mapRowToProduct(results);
        }
        return product;
    }

    private Product mapRowToProduct(SqlRowSet rs){
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setProduct_sku(rs.getString("product_sku"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setImage_name(rs.getString("image_name"));
        return product;
    }
}

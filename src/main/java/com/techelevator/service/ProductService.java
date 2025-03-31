package com.techelevator.service;

import com.techelevator.dao.JdbcProductDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        products = productDao.getProducts();
        return products;
    }

    public List<Product> getProductsWithSKU(String SKU){
        List<Product> products = new ArrayList<>();
        products = productDao.getProductsWithSKU(SKU);
        return products;
    }

    public List<Product> getProductsWithName(String name){
        List<Product> products = new ArrayList<>();
        products = productDao.getProductsWithName(name);
        return products;
    }

    public Product getProductWithId(Integer id){
        Product product = new Product();
        product = productDao.getProductWithId(id);
        return product;
    }
}

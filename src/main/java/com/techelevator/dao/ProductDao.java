package com.techelevator.dao;

import com.techelevator.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    List<Product> getProductsWithSKU(String sku);

    List<Product> getProductsWithName(String name);

    Product getProductWithId(Integer id);

}

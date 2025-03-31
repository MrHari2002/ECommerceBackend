package com.techelevator.controller;

import com.techelevator.model.Product;
import com.techelevator.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProducts(@RequestParam(required = false) String sku, @RequestParam(required = false) String name){
        List<Product> products = new ArrayList<>();
        if(sku != null) {
            products = productService.getProductsWithSKU(sku);
        } else if (name != null) {
            products = productService.getProductsWithName(name);
        } else{
            products = productService.getProducts();
        }
        return products;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getProductWithId(@PathVariable int id){
        Product product = null;
        product = productService.getProductWithId(id);
        return product;
    }

}

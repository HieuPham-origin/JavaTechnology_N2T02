package com.example.lab9.service;

import com.example.lab9.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Optional<Product> getProductById(int id);
    void deleteProduct(int id);
    Product updateProduct(int id, Product product);
}

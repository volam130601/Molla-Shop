package com.molla.service;

import com.molla.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findByName(String productName);

    Product findById(Long productId);

    Product save(Product product);

    void deleteById(Long productId);
}

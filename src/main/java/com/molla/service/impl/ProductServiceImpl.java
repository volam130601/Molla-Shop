package com.molla.service.impl;

import com.molla.entity.Product;
import com.molla.repository.ProductRepository;
import com.molla.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findByName(String productName) {
        return repository.findByName(productName);
    }

    @Override
    public Product findById(Long productId) {
        return repository.findById(productId).get();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(Long productId) {
        repository.deleteById(productId);
    }
}

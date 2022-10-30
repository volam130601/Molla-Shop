package com.molla.service;

import com.molla.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findByCode(String code);

    Category findById(Long categoryId);

    Category save(Category category);

    void deleteById(Long categoryId);
}

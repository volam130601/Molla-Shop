package com.molla.service.impl;

import com.molla.entity.Category;
import com.molla.repository.CategoryRepository;
import com.molla.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByCode(String code) {
        return categoryRepository.findByCode(code);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> findCategoriesByCategory_Id(Long categoryId) {
        return categoryRepository.findCategoriesByCategory_Id(categoryId);
    }
}

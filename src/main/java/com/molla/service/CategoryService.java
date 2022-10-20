package com.molla.service;

import com.molla.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findByCode(String code);

    //    List<Category> findByParentId(Long parentId);
    List<Category> findCategoriesByCategory_Id(Long categoryId);


    Category save(Category category);

    void deleteById(Long categoryId);
}

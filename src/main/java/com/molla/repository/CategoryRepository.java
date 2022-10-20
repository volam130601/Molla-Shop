package com.molla.repository;

import com.molla.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCode(String code);

    List<Category> findCategoriesByCategory_Id(Long categoryId);
}

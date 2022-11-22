package com.molla.service;

import com.molla.entity.Category;
import com.molla.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    void findAll() {
        assertTrue(repository.findAll().size() > 0);
    }

    @Test
    void findByCode() {
        assertNotNull(repository.findByCode("PHONE"));
    }

    @Test
    void save() {
        if (repository.findAll() != null) {
            assertNotNull(repository.save(Category.builder().code("PHONE").name("Smart Phone").build()));
            assertNotNull(repository.save(Category.builder().code("TV").name("Televisions").build()));
            assertNotNull(repository.save(Category.builder().code("LAPTOP").name("Laptops").build()));
            assertNotNull(repository.save(Category.builder().code("MONITOR").name("Monitors").build()));
            assertNotNull(repository.save(Category.builder().code("IPAD").name("IPad & Tablets").build()));
        }
    }

    @Test
    void deleteById() {
    }
}
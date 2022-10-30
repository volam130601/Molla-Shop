package com.molla.service;

import com.molla.entity.Brand;
import com.molla.entity.Category;
import com.molla.repository.BrandRepository;
import com.molla.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InitDataTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;


    @Test
    void save() {
        if (categoryRepository.findByCode("PHONE") == null) {
            categoryRepository.save(Category.builder().code("PHONE").name("Smart Phone").build());
            categoryRepository.save(Category.builder().code("TV").name("Televisions").build());
            categoryRepository.save(Category.builder().code("LAPTOP").name("Laptops").build());
            categoryRepository.save(Category.builder().code("MONITOR").name("Monitors").build());
            categoryRepository.save(Category.builder().code("IPAD").name("IPad & Tablets").build());
        }
        if (brandRepository.findByCode("APPlE") == null) {
            brandRepository.save(Brand.builder().code("APPLE").name("Apple").build());
            brandRepository.save(Brand.builder().code("SAMSUNG").name("Samsung").build());
            brandRepository.save(Brand.builder().code("PANASONIC").name("Panasonic").build());
            brandRepository.save(Brand.builder().code("OPPO").name("Oppo").build());
            brandRepository.save(Brand.builder().code("DELL").name("Dell").build());
        }
    }
}

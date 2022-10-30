package com.molla.service;

import com.molla.entity.Brand;
import com.molla.entity.Category;
import com.molla.entity.Product;
import com.molla.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void save() {
        Brand brand = brandService.findByCode("SAMSUNG");
        Category category = categoryService.findByCode("PHONE");
        Product product = Product.builder()
                .name("Samsung Galaxy Ultra 14S")
                .shortDescription("So beautilful.")
                .fullDescription("So beautiful 1123")
                .price(2000d)
                .quantity(200)
                .discount(0.2d)
                .user(userService.findByEmail("admin@gmail.com"))
                .brand(brand)
                .category(category)
                .mainImage("123")
                .extraImage1("123")
                .extraImage2("123")
                .extraImage3("123")
                .inStock(true)
                .enabled(true)
                .build();
        productService.save(product);
    }

    @Test
    void deleteById() {
        productRepository.deleteAll();
    }
}
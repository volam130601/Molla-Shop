package com.molla.service;

import com.molla.entity.Brand;
import com.molla.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BrandServiceTest {
    @Autowired
    BrandRepository repository;

    @Test
    void findAll() {
        assertNotNull(repository.findAll());
    }

    @Test
    void findByCode() {
        if (repository.findAll() != null) {
            repository.save(Brand.builder().code("APPLE").build());
            repository.save(Brand.builder().code("SAMSUNG").build());
            repository.save(Brand.builder().code("PANASONIC").build());
            repository.save(Brand.builder().code("OPPO").build());
            repository.save(Brand.builder().code("DELL").build());
        }
    }

    @Test
    void save() {
    }
}
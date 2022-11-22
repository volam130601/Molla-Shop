package com.molla.service;

import com.molla.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand findByCode(String code);

    Brand findById(Long brandId);

    Brand save(Brand brand);

    void deleteById(Long brandId);
}

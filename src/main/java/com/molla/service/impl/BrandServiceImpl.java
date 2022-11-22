package com.molla.service.impl;

import com.molla.entity.Brand;
import com.molla.repository.BrandRepository;
import com.molla.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findByCode(String code) {
        return brandRepository.findByCode(code);
    }

    @Override
    public Brand findById(Long brandId) {
        return brandRepository.findById(brandId).get();
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteById(Long brandId) {
        brandRepository.deleteById(brandId);
    }
}

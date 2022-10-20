package com.molla.repository;

import com.molla.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByCode(String code);
}

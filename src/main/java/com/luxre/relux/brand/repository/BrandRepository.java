package com.luxre.relux.brand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxre.relux.brand.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}

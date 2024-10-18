package com.luxre.relux.brand.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luxre.relux.brand.domain.Brand;
import com.luxre.relux.brand.repository.BrandRepository;

@Service
public class BrandService {

	private BrandRepository brandRepository;
	
	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}
	
	public List<Brand> allBrandList() {
		return brandRepository.findAll();
	}
}

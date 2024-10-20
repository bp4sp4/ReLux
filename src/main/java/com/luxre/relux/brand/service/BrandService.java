package com.luxre.relux.brand.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.luxre.relux.brand.domain.Brand;
import com.luxre.relux.brand.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    // 생성자 주입
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // 전체 브랜드 리스트 가져오기
    public List<Brand> allBrandList() {
        return brandRepository.findAll();
    }

    // 필터링된 브랜드 리스트 가져오기
    public List<Brand> getFilteredBrands(String age, String brand, int maxPrice) {
        System.out.println("나이 : " + age + ", 브랜드 : " + brand + ", 최대 가격 : " + maxPrice);
        
        // 브랜드가 없을 경우 전체 리스트 반환
        if (brand == null || brand.isEmpty()) {
            return brandRepository.findAll();
        }

        // "전체" 브랜드 선택 시 나이에 따른 필터링
        if ("전체".equals(brand)) {
            // 나이에 따른 필터링
            if ("10".equals(age)) {
                return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(10, maxPrice);
            }
            if ("20".equals(age)) {
                return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(20, maxPrice);
            }
            if ("30".equals(age)) {
                return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(30, maxPrice);
            }
            if ("40".equals(age)) {
                return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(40, maxPrice);
            }
            if ("50".equals(age)) {
                return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(50, maxPrice);
            }
            // 나이 선택이 없을 경우 전체 리스트 반환
            return brandRepository.findAll();
        }
        
        // 나이가 10대이고 특정 브랜드에 대한 필터링
        if ("10".equals(age)) {
            return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(10, brand, maxPrice);
        }
        if ("20".equals(age)) {
            return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(20, brand, maxPrice);
        }
        
        if ("30".equals(age)) {
            return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(30, brand, maxPrice);
        }
        
        if ("40".equals(age)) {
            return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(40, brand, maxPrice);
        }
        
        if ("50".equals(age)) {
            return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(50, brand, maxPrice);
        }
        
        // 나이와 관계없이 브랜드와 가격으로 필터링
        if (maxPrice <= 0) {
            return brandRepository.findByBrandnameAndBrandPriceLessThanEqual(brand, maxPrice);
        }

        // 나이와 관계없이 브랜드만 필터링
        return brandRepository.findByBrandname(brand);
    }
}

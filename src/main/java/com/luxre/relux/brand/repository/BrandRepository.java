package com.luxre.relux.brand.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.luxre.relux.brand.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    // 브랜드 이름으로 리스트 가져오기
    List<Brand> findByBrandname(String brandname);
    
    // 브랜드 이름과 가격으로 필터링
    List<Brand> findByBrandnameAndBrandPriceLessThanEqual(String brandname, int maxPrice);
    
    // 나이와 브랜드이름과 가격 필터링
    List<Brand> findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(int brandAgeGroup, String brand, int maxprice);

    
    // 조건걸고 다시 전체 골랐을때 리스트 필터링
	List<Brand> findByBrandAgeGroupAndBrandPriceGreaterThanEqual(int brandAgeGroup, int maxPrice);
}

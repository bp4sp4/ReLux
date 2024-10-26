package com.luxre.relux.brand.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luxre.relux.brand.domain.Brand;
import com.luxre.relux.brand.dto.BrandMainDto;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

	// 브랜드 이름으로 리스트 가져오기 // ex) 나이: 전체 또는 특정 연령대, 브랜드: 구찌, 샤넬 등
	List<Brand> findByBrandname(String brandname);

	// 나이, 브랜드 이름, 가격 조건으로 필터링 // ex) 나이: 10대, 브랜드: 샤넬, 가격: 100만원 이상
	List<Brand> findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(int brandAgeGroup, String brand,
			int maxPrice);

	// 나이와 가격으로 필터링 // ex) 나이: 10대, 브랜드: 전체, 가격: 100만원 이상
	List<Brand> findByBrandAgeGroupAndBrandPriceGreaterThanEqual(int brandAgeGroup, int maxPrice);

	// 연령대와 관계없이 가격 조건으로 필터링 // ex) 가격: 100만원 이상
	List<Brand> findByBrandPriceGreaterThanEqual(int maxPrice);

	// 특정 브랜드 이름과 가격 조건으로 필터링 // ex) 브랜드: 구찌, 가격: 100만원 이상
	List<Brand> findByBrandnameAndBrandPriceGreaterThanEqual(String brand, int maxPrice);

	@Query("SELECT new com.luxre.relux.brand.dto.BrandMainDto(b.brandname, b.brandImagePath) "
			+ "FROM Brand b WHERE LOWER(b.brandname) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<BrandMainDto> findByKeyword(@Param("keyword") String keyword);

}
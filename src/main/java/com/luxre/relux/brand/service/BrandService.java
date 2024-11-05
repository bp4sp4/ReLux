package com.luxre.relux.brand.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxre.relux.brand.domain.Brand;
import com.luxre.relux.brand.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    
    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/brandimg";

    public List<String> getRandomImages(int count) {
        try {
            Path dirPath = Paths.get(IMAGE_DIRECTORY);
            List<String> allImages = Files.list(dirPath)
                .filter(Files::isRegularFile)
                .filter(path -> {
                    String fileName = path.getFileName().toString().toLowerCase();
                    return fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".svg") || fileName.endsWith(".webp");
                })
                .map(path -> "/images/brandimg/" + path.getFileName().toString()) // URL 경로로 변경
                .collect(Collectors.toList());

            Collections.shuffle(allImages); // 무작위 섞기
            return allImages.stream().limit(count).collect(Collectors.toList()); // 상위 count개 선택
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }



    // 생성자 주입
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // 전체 브랜드 리스트 가져오기
    public List<Brand> allBrandList() {
        return brandRepository.findAll();
    }
    
    public Page<Brand> getPageBrandList(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    // 필터링된 브랜드 리스트 가져오기
    public List<Brand> getFilteredBrands(String age, String brand, int maxPrice) {
        System.out.println("나이 : " + age + ", 브랜드 : " + brand + ", 최대 가격 : " + maxPrice);
        
        // 나이와 브랜드가 모두 "전체" 일 경우, 최대 가격에 맞는 전체 브랜드 리스트 출력
        // ex ) 연령대 : 전체, 브랜드 : 전체 가격대 : 100~1000만원 가격 변동에 따른 리스트 가져옴
        if ("전체".equals(age) && "전체".equals(brand)) {
            return brandRepository.findByBrandPriceGreaterThanEqual(maxPrice);
        }
        
        // 이것만안됨 --> 나이 : 전체  브랜드 : 구찌 ,샤넬 등등 가격 변동에 따른 리스트 출력
        if ("전체".equals(age)) {
        	 return brandRepository.findByBrandnameAndBrandPriceGreaterThanEqual(brand, maxPrice);
        }

        // 브랜드 전체일 경우, 나이에 따라 필터링
        if ("전체".equals(brand)) {
            if (age != null && !age.isEmpty()) {
                switch (age) {
                    case "10":
                        return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(10, maxPrice);
                    case "20":
                        return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(20, maxPrice);
                    case "30":
                        return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(30, maxPrice);
                    case "40":
                        return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(40, maxPrice);
                    case "50":
                        return brandRepository.findByBrandAgeGroupAndBrandPriceGreaterThanEqual(50, maxPrice);
                    default:
                        return brandRepository.findByBrandPriceGreaterThanEqual(maxPrice); 
                }
            } else {
                return brandRepository.findByBrandPriceGreaterThanEqual(maxPrice); 
            }
        }

        // 특정 나이와 브랜드에 대한 필터링
        if (age != null && !age.isEmpty()) {
            switch (age) {
                case "10":
                    return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(10, brand, maxPrice);
                case "20":
                    return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(20, brand, maxPrice);
                case "30":
                    return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(30, brand, maxPrice);
                case "40":
                    return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(40, brand, maxPrice);
                case "50":
                    return brandRepository.findByBrandAgeGroupAndBrandnameAndBrandPriceGreaterThanEqual(50, brand, maxPrice);
                default:
                    return brandRepository.findByBrandname(brand);
            }
        }
        
        // 기본적으로 브랜드 이름으로 필터링
        return brandRepository.findByBrandname(brand);
    }
}
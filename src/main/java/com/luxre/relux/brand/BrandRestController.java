package com.luxre.relux.brand;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxre.relux.brand.domain.Brand;
import com.luxre.relux.brand.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandRestController {

    private final BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }
    
    @GetMapping("/filterList")
    public List<Brand> filterBrandList(
            @RequestParam(value = "age", required = false) String age,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "10000000") int maxPrice) {

        return brandService.getFilteredBrands(age, brand, maxPrice);
    }
    
    

}

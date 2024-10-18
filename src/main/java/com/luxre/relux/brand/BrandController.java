package com.luxre.relux.brand;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luxre.relux.brand.domain.Brand;
import com.luxre.relux.brand.service.BrandService;

@RequestMapping("/brand")
@Controller
public class BrandController {
	
	private  BrandService brandService;
	
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("/list-view")
	public String goBrand(Model model) {
		List<Brand> brands = brandService.allBrandList();
		model.addAttribute("brand", brands);
		return "brand/list-view";
	}
}

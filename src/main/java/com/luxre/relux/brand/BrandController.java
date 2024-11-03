package com.luxre.relux.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luxre.relux.brand.domain.Brand;
import com.luxre.relux.brand.service.BrandService;

@RequestMapping("/brand")
@Controller
public class BrandController {

	private BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("/list-view")
	public String goBrand(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		Pageable pageable = PageRequest.of(page - 1, size); 
		Page<Brand> brandPage = brandService.getPageBrandList(pageable);

		int currentPage = brandPage.getNumber() + 1; 
		int totalPages = brandPage.getTotalPages();
		int pageSize = 10; 
		int currentPageGroup = (currentPage - 1) / pageSize + 1;
		int startPage = (currentPageGroup - 1) * pageSize + 1;
		int endPage = Math.min(startPage + pageSize - 1, totalPages);

		model.addAttribute("brands", brandPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		

		return "brand/list-view";
	}

}

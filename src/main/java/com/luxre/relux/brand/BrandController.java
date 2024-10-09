package com.luxre.relux.brand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/brand")
@Controller
public class BrandController {

	@GetMapping("/list-view")
	public String goBrand() {
		return "brand/list-view";
	}
}

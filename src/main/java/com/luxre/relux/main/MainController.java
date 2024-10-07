package com.luxre.relux.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/main")
@Controller
public class MainController {

	@GetMapping("/list-view")
	public String gomain() {
		return "main/main";
	}
}

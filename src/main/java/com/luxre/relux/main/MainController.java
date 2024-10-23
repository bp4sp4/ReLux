package com.luxre.relux.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luxre.relux.main.service.MainService;
@RequestMapping("/main")
@Controller
public class MainController {
	
	@Autowired
    private MainService mainService;
	
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}

	@GetMapping("/list-view")
	public String gomain(Model model) {
	    List<Object[]> topPosts = mainService.getTopPosts(); 
	    model.addAttribute("topPosts", topPosts);
	    return "main/main";
	}
}

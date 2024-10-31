package com.luxre.relux.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luxre.relux.banner.service.BannerService;
import com.luxre.relux.brand.dto.BrandMainDto;
import com.luxre.relux.brand.repository.BrandRepository;
import com.luxre.relux.main.service.MainService;
import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.repository.PostRepository;
@RequestMapping("/main")
@Controller
public class MainController {
	
	@Autowired
	 
	private BannerService bannerService;
    private MainService mainService;
	private PostRepository postRepository;
	private BrandRepository brandRepository;
	
	public MainController(MainService mainService, PostRepository postRepository, BrandRepository brandRepository,BannerService bannerService) {
		this.mainService = mainService;
		this.postRepository = postRepository;
		this.brandRepository = brandRepository;
		this.bannerService = bannerService;
	}

	@GetMapping("/list-view")
	public String main(Model model) {
	    model.addAttribute("banners", bannerService.getBanners());
	    List<Object[]> topPosts = mainService.getTopPosts();
	    model.addAttribute("topPosts", topPosts);
	    return "main/main";
	}

	 
	 @GetMapping("/search")
	    public String search(@RequestParam("keyword") String keyword, Model model) {
	        List<BrandMainDto> brands = mainService.searchBrands(keyword);
	        List<Post> posts = mainService.searchPosts(keyword);
	        
	        model.addAttribute("brands", brands);
	        model.addAttribute("posts", posts);
	        return "main/searchresult";
	    }
	 


	   
	 
}


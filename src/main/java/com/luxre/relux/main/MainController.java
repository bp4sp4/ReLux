package com.luxre.relux.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luxre.relux.brand.dto.BrandMainDto;
import com.luxre.relux.brand.repository.BrandRepository;
import com.luxre.relux.main.service.MainService;
import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.repository.PostRepository;
@RequestMapping("/main")
@Controller
public class MainController {
	
	@Autowired
    private MainService mainService;
	private PostRepository postRepository;
	private BrandRepository brandRepository;
	
	public MainController(MainService mainService, PostRepository postRepository, BrandRepository brandRepository) {
		this.mainService = mainService;
		this.postRepository = postRepository;
		this.brandRepository = brandRepository;
	}

	 @GetMapping("/list-view")
	    public String gomain(Model model) {
	        List<Object[]> topPosts = mainService.getTopPosts();
	        model.addAttribute("topPosts", topPosts);
	        return "main/main";
	    }
	 
	 @GetMapping("/search")
	    public String search(@RequestParam("keyword") String keyword, Model model) {
	        List<BrandMainDto> brands = mainService.searchBrands(keyword);
	        List<Post> posts = mainService.searchPosts(keyword);
	        
	        // 콘솔에 출력
	        System.out.println("검색 키워드: " + keyword);
	        System.out.println("검색된 브랜드 수: " + brands.size());
	        System.out.println("검색된 게시글 수: " + posts.size());
	        
	        // 게시글 내용을 콘솔에 출력
	        for (Post post : posts) {
	            System.out.println("게시글 제목: " + post.getTitle());
	            System.out.println("게시글 내용: " + post.getContents());
	        }
	        
	        model.addAttribute("brands", brands);
	        model.addAttribute("posts", posts);
	        return "main/searchresult";
	    }
}

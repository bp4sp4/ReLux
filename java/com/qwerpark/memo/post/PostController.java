package com.qwerpark.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qwerpark.memo.dto.CardView;
import com.qwerpark.memo.post.domain.Post;
import com.qwerpark.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/timeline-view")
	public String memoList(Model model, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<CardView> cardViewList = postService.getPostList();
		
		model.addAttribute("cardViewList", cardViewList);
		
		return "post/list";
	}
	
	@GetMapping("/create-view")
	public String inputMemo() {
		return "post/list";
	}
	
	
}

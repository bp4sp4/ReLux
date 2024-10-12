package com.luxre.relux.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@GetMapping("/list-view")
	public String PostList() {
		return "post/list-view";
	}
	
	@GetMapping("/write-view")
	public String PostWrite() {
		return "post/write-view";
	}
	
	@GetMapping("/detail-view")
	public String PostDetail() {
		//pathvariable
		return "post/detail-view";
	}
}

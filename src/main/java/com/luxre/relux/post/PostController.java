package com.luxre.relux.post;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.dto.PostDto;
import com.luxre.relux.post.service.PostService;
import com.luxre.relux.user.domain.User;
import com.luxre.relux.user.service.UserService;

@RequestMapping("/post")
@Controller
public class PostController {

	private final PostService postService;
	private final UserService userService;

	public PostController(PostService postService, UserService userService) {
		this.postService = postService;
		this.userService = userService;
	}

	@GetMapping("/list-view")
	public String postList(
	        @RequestParam(name = "page", defaultValue = "1") int page,
	        @RequestParam(name = "size", defaultValue = "8") int size, Model model) {

	    Page<PostDto> postDtoPage = postService.getPageList(page - 1, size);

	    int currentPage = postDtoPage.getNumber() + 1;
	    int totalPages = postDtoPage.getTotalPages();
	    int pageSize = 10;
	    int currentPageGroup = (currentPage - 1) / pageSize + 1;  
	    int startPage = (currentPageGroup - 1) * pageSize + 1; 
	    int endPage = Math.min(startPage + pageSize - 1, totalPages); 

	    model.addAttribute("posts", postDtoPage.getContent());   
	    model.addAttribute("currentPage", currentPage);           
	    model.addAttribute("totalPages", totalPages);            
	    model.addAttribute("startPage", startPage);              
	    model.addAttribute("endPage", endPage);                

	    return "post/list-view";
	}

	@GetMapping("/write-view")
	public String PostWrite() {
		return "post/write-view";
	}

	@GetMapping("/detail-view/{id}")
	public String postDetail(@PathVariable("id") int id, Model model) {
		// 게시글 정보 가져오기
		Post post = postService.getPostDetail(id);

		// 사용자 정보 가져오기
		User user = userService.getUserById(post.getUserId());

		// 이미지 태그를 제거한 내용 생성
		// 순수한 텍스트만, 줄바꿈 필요없음
		String cleanedContents = Jsoup.clean(post.getContents(), "", Whitelist.none(),
				new Document.OutputSettings().prettyPrint(false));

		// 모델에 게시글과 사용자 정보를 추가
		model.addAttribute("post", post);
		model.addAttribute("user", user);
		model.addAttribute("cleanedContents", cleanedContents);

		return "post/detail-view";
	}
	
	@GetMapping("/update-view/{id}")
	public String showUpdateView(@PathVariable("id") int id, Model model) {
	    Post post = postService.getPostById(id);
	    
	    if (post == null) {
	        return "redirect:/post/list"; // 게시물이 없을 경우
	    }
	    
	    String userName = userService.getUserNameById(post.getUserId());
	    PostDto postDto = new PostDto(
	        post.getId(), 
	        post.getTitle(),
	        post.getContents(),
	        post.getImagePath(),
	        userName
	    );
	    
	    model.addAttribute("post", postDto);
	    return "post/modify-view"; // 수정 뷰로 이동
	}






}

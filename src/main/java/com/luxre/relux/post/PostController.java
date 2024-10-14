package com.luxre.relux.post;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public PostController(PostService postService,UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }
    
    @GetMapping("/list-view")
    public String postList(Model model) {
        // PostService에서 전체 게시글 목록을 가져온다.
        List<PostDto> postDtoList = postService.getPostListUserName();

        model.addAttribute("posts", postDtoList);
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
        String cleanedContents = Jsoup.clean(post.getContents(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
        
        // 모델에 게시글과 사용자 정보를 추가
        model.addAttribute("post", post);
        model.addAttribute("user", user);
        model.addAttribute("cleanedContents", cleanedContents); // 정리된 내용을 추가
        
        return "post/detail-view";
    }
}

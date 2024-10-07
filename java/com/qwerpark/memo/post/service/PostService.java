package com.qwerpark.memo.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qwerpark.memo.common.FileManager;
import com.qwerpark.memo.dto.CardView;
import com.qwerpark.memo.post.domain.Post;
import com.qwerpark.memo.post.repository.PostRepository;
import com.qwerpark.memo.user.domain.User;
import com.qwerpark.memo.user.service.UserService;

@Service
public class PostService {
    
    private final PostRepository postRepository;
    private UserService userService;
    
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }
    
    // 글작성
    public Post addPost(int userId, String title, String contents, MultipartFile file) {
        String urlPath = null;

        // 파일이 null이 아니고 비어있지 않은 경우에만 파일 저장 처리
        if (file != null && !file.isEmpty()) {
            urlPath = FileManager.saveFile(userId, file);
        }

        // Post 객체를 이미지 경로가 null일 수 있게 설정
        Post post = Post.builder()
                        .userId(userId)
                        .title(title)
                        .contents(contents)
                        .imagePath(urlPath)  // 이미지가 없으면 null
                        .build();

        return postRepository.save(post);
    }
    // 게시물을 내림차순
    public List<CardView> getPostList() {
    	
    	List<Post> postList =  postRepository.findAllByOrderByIdDesc();
    	
    	List<CardView> cardViewList = new ArrayList<>();
    	
    	for(Post post:postList) {
    		
    		int userId = post.getUserId();
    		User user = userService.getUserById(userId);
    		
    		CardView cardView = CardView.builder()
					    		.postId(post.getId())
					    		.userId(userId)
					    		.contents(post.getContents())
					    		.imagePath(post.getImagePath())
					    		.loginId(user.getLoginId())
					    		.build();
    		
    		cardViewList.add(cardView);
    	}
        return cardViewList;
    }
    
    // id로 게시물이 있으면 나오게하고 아니면 널 반환
    public Post getPost(int id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }
}

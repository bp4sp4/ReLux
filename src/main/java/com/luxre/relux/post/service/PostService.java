package com.luxre.relux.post.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.dto.PostDto;
import com.luxre.relux.post.repository.PostRepository;
import com.luxre.relux.user.service.UserService;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }
    
    public List<PostDto> getPostListUserName() {
        List<Post> posts = postRepository.findAllByOrderByIdDesc();
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : posts) {

            String userName = userService.getUserNameById(post.getUserId());

            postDtoList.add(new PostDto(
                post.getId(),
                post.getTitle(),      
                post.getContents(),   
                post.getImagePath(),  
                userName              
            ));
        }

        return postDtoList;
    }

    
    // 게시글 목록 가져오기
    public List<Post> getPostList() {
        return postRepository.findAllByOrderByIdDesc();
    }

    // 특정 게시글 상세 정보 가져오기
    public Post getPostDetail(int id) {
        return postRepository.findById(id).orElse(null);
    }
    
    // 글작성
    public Post addPost(int userId, String title, String contents, String imagePath) {
        Post post = Post.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .imagePath(imagePath)
                .build();
        return postRepository.save(post);
    }

 
}

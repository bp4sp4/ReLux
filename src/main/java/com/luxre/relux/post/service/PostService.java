package com.luxre.relux.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.dto.PostDto;
import com.luxre.relux.post.repository.PostRepository;
import com.luxre.relux.user.service.UserService;

import jakarta.transaction.Transactional;

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
                userName));
        }

        return postDtoList;
    }

    // 페이징 처리
    public Page<PostDto> getPageList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);
        
        List<PostDto> postDtoList = new ArrayList<>();
        
        for (Post post : posts) {
            String userName = userService.getUserNameById(post.getUserId());
            PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContents(),
                post.getImagePath(),
                userName
            );
            postDtoList.add(postDto);
        }
        
        return new PageImpl<>(postDtoList, pageable, posts.getTotalElements());
    }

    // 게시글 목록 가져오기
    public List<Post> getPostList() {
        return postRepository.findAllByOrderByIdDesc();
    }

    // 특정 게시글 상세 정보 가져오기
    public Post getPostDetail(int id) {
        return postRepository.findById(id).orElse(null);
    }

    // 글 작성
    public Post addPost(int userId, String title, String contents, String imagePath) {
        Post post = Post.builder().userId(userId).title(title).contents(contents).imagePath(imagePath).build();
        return postRepository.save(post);
    }

    // 게시물 업데이트
    public Post updatePost(int id, String title, String contents, String imagePath) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(title);
            post.setContents(contents);
            if (imagePath != null && !imagePath.isEmpty()) {
                post.setImagePath(imagePath);
            }
            return postRepository.save(post); 
        }
        return null; 
    }

    // 게시물 ID로 조회
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null); 
    }
    
    
    // 게시글 삭제 
    public boolean deletePost(int postId, int userId) {
        // 게시글을 ID로 조회
        Post post = postRepository.findById(postId).orElse(null);

        // 게시물이 존재하고, 작성자가 맞는지 확인
        if (post != null && post.getUserId() == userId) {
            postRepository.delete(post); // 게시물 삭제
            return true;
        }
        return false;
    }

}

package com.luxre.relux.postview.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luxre.relux.postview.domain.PostView;
import com.luxre.relux.postview.repository.PostViewRepository;

@Service
public class PostViewService {
    
    private final PostViewRepository postViewRepository;

    public PostViewService(PostViewRepository postViewRepository) {
        this.postViewRepository = postViewRepository;
    }

    @Transactional
    public void addPostView(int postId, int userId) {
    	
        // 사용자가 해당 게시물을 조회한 기록이 있는지 확인
        Optional<PostView> existingView = postViewRepository.findByPostIdAndUserId(postId, userId);
        
        // 조회한 기록이 없다면 새로 추가
        if (!existingView.isPresent()) {
            PostView postView = PostView.builder()
                    .postId(postId)
                    .userId(userId)
                    .build();
            postViewRepository.save(postView);
        }
    }
    // 특정 게시물 횟수 추가
    public int getPostViewCount(int postId) {
        return postViewRepository.countByPostId(postId);
    }
}

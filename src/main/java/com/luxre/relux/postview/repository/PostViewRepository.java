package com.luxre.relux.postview.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.luxre.relux.postview.domain.PostView;

public interface PostViewRepository extends JpaRepository<PostView, Integer> {
	
	// 특정 게시글의 조회수 (조회 기록의 개수)
    public int countByPostId(int postId);

    // 특정 게시글을 특정 사용자가 이미 조회했는지 확인
    Optional<PostView> findByPostIdAndUserId(int postId, int userId);

}

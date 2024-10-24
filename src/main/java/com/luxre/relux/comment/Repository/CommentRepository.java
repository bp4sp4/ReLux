package com.luxre.relux.comment.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxre.relux.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	// 특정 게시글의 모든 댓글 조회
    List<Comment> findByPostId(int postId);
    // 특정 댓글 단일 객체를 조회하는 메서드입니다.
    public Comment findById(int id); 
}

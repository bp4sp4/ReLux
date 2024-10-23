package com.luxre.relux.comment.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxre.relux.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(int postId);
    Comment findById(int id); 
}

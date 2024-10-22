package com.luxre.relux.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luxre.relux.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	// 리스트
	public List<Post> findAllByOrderByIdDesc();
	// 페이징
	Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
	
	public Optional<Post> findById(int id);
}

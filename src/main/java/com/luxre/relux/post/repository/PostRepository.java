package com.luxre.relux.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxre.relux.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findAllByOrderByIdDesc();
}

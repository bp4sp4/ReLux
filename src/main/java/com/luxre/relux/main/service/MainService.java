package com.luxre.relux.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxre.relux.post.repository.PostRepository;

@Service
public class MainService {
	@Autowired
    private PostRepository postRepository;

	public List<Object[]> getTopPosts() {
	    Pageable pageable = PageRequest.of(0, 4); 
	    return postRepository.findTopPostsWithViewCount(pageable);
	}
}

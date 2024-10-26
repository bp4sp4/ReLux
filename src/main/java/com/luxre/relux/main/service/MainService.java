package com.luxre.relux.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxre.relux.brand.dto.BrandMainDto;
import com.luxre.relux.brand.repository.BrandRepository;
import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.repository.PostRepository;

@Service
public class MainService {
	@Autowired
    private PostRepository postRepository;
	private BrandRepository brandRepository;
	
	public MainService(BrandRepository brandRepository, PostRepository postRepository) {
        this.brandRepository = brandRepository;
        this.postRepository = postRepository;
    }

	public List<Object[]> getTopPosts() {
	    Pageable pageable = PageRequest.of(0, 4); 
	    return postRepository.findTopPostsWithViewCount(pageable);
	}
	
	public List<BrandMainDto> searchBrands(String keyword) {
	    return brandRepository.findByKeyword(keyword);
	}
	
	   public List<Post> searchPosts(String keyword) {
	        return postRepository.searchByKeyword(keyword);
	    }

	  
	  
}

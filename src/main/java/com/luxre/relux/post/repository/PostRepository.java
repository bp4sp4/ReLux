package com.luxre.relux.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.dto.PostMainDto;

public interface PostRepository extends JpaRepository<Post, Integer> {

	// 리스트
	public List<Post> findAllByOrderByIdDesc();

	// 페이징
	Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

	public Optional<Post> findById(int id);

	@Query(value = "SELECT p, p.views, u.name " +
            "FROM Post p LEFT JOIN User u ON p.userId = u.id " +
            "ORDER BY p.views DESC")
	List<Object[]> findTopPostsWithViewCount(Pageable pageable);



	// 검색 키워드
	 @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.contents LIKE %:keyword%")
	    List<Post> searchByKeyword(@Param("keyword") String keyword);

}

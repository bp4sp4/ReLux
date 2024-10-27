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

	@Query(value = "SELECT p, COUNT(pv.id) AS viewCount, u.name " +
            "FROM Post p LEFT JOIN PostView pv ON p.id = pv.postId " +
            "LEFT JOIN User u ON p.userId = u.id " +
            "GROUP BY p.id, u.name ORDER BY viewCount DESC")
	List<Object[]> findTopPostsWithViewCount(Pageable pageable);



	 @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.contents LIKE %:keyword%")
	    List<Post> searchByKeyword(@Param("keyword") String keyword);

}

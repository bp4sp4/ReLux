package com.luxre.relux.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luxre.relux.comment.Repository.CommentRepository;
import com.luxre.relux.comment.domain.Comment;
import com.luxre.relux.comment.dto.CommentDto;
import com.luxre.relux.user.repository.UserRepository;
import com.luxre.relux.user.service.UserService;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;

	public CommentService(CommentRepository commentRepository, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}

	// 댓글작성
	public Comment addComment(int postId, int userId, String contents) {
		Comment comment = new Comment();
		comment.setPostId(postId);
		comment.setUserID(userId);
		comment.setContents(contents);
		// 댓글 아이디 내용 db 저장
		return commentRepository.save(comment);
	}
	
	// Dto는 객체를 detail 에 넘겨줘야
	public List<CommentDto> getComment(int postId) {
	    List<Comment> comments = commentRepository.findByPostId(postId);
	    List<CommentDto> commentDtos = new ArrayList<>();

	    for (Comment comment : comments) {
	        String userName = userService.getUserNameById(comment.getUserID()); 
	        CommentDto commentDto = CommentDto.builder()
	                .id(comment.getId())
	                .postId(comment.getPostId())
	                .userId(comment.getUserID())
	                .userName(userName) 
	                .contents(comment.getContents())
	                .createdAt(comment.getCreatedAt())
	                .build();
	        commentDtos.add(commentDto);
	    }

	    return commentDtos;
	}
	
	// 댓글 수정 
	public boolean updateComment(int commentId, int userId, String contents) {
	    Comment comment = commentRepository.findById(commentId);
	    if (comment != null && comment.getUserID() == userId) { // 사용자 확인
	        comment.setContents(contents);
	        commentRepository.save(comment);
	        return true;
	    }
	    return false;
	}
    // 댓글 삭제
	public boolean deleteComment(int commentId, int userId) {
	    Comment comment = commentRepository.findById(commentId);
	    if (comment != null && comment.getUserID() == userId) { // 사용자 확인
	        commentRepository.delete(comment); // 댓글 삭제
	        return true;
	    }
	    return false;
	}



}

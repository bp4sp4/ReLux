package com.luxre.relux.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luxre.relux.comment.domain.Comment;
import com.luxre.relux.comment.dto.CommentUpdateRequest;
import com.luxre.relux.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
		
	@Autowired
    private CommentService commentService;
	
	@PostMapping("/create")
    public Map<String, String> addComment(
            @RequestParam("postId") int postId,
            @RequestParam("contents") String contents,
            HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");

        Comment comment = commentService.addComment(postId, userId, contents);

        Map<String, String> resultMap = new HashMap<>();
        if (comment != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }
	

	@GetMapping("/api/userId")
	@ResponseBody
	public int getUserId(HttpSession session) {
	    Integer userId = (Integer) session.getAttribute("userId");
	    return userId != null ? userId : 0;
	}

	
	@PostMapping("/update")
	public Map<String, String> modifyComment(@RequestBody CommentUpdateRequest request) {
	    int commentId = request.getCommentId();
	    String contents = request.getContents();
	    int userId = request.getUserId();

	    // 댓글 수정 요청을 처리
	    boolean isUpdated = commentService.updateComment(commentId, userId, contents);

	    Map<String, String> resultMap = new HashMap<>();
	    if (isUpdated) {
	        resultMap.put("result", "success");
	    } else {
	        resultMap.put("result", "fail");
	        resultMap.put("message", "수정 권한이 없습니다.");
	    }

	    return resultMap;
	}

	
    
    @PostMapping("/delete")
    public Map<String, String> deleteComment(
            @RequestParam("commentId") int commentId,
            @RequestParam("userId") int userId) {

        boolean isDeleted = commentService.deleteComment(commentId, userId);

        Map<String, String> resultMap = new HashMap<>();
        if (isDeleted) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
            resultMap.put("message", "삭제 권한이 없습니다.");
        }

        return resultMap;
    }
}

package com.luxre.relux.post;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luxre.relux.common.FileManager;
import com.luxre.relux.post.domain.Post;
import com.luxre.relux.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {

	private PostService postService;
	

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public Map<String, String> createPost(
            @RequestParam("title") String title,
            @RequestParam("contents") String contents,
            @RequestParam(value = "imagePath", required = false) String imagePath,
            HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");

        // imagePath를 사용하여 포스트 생성
        Post post = postService.addPost(userId, title, contents, imagePath);

        Map<String, String> resultMap = new HashMap<>();
        if (post != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }
    
    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {

        // 로그인한 사용자 정보 가져오기 (userId)
        int userId = (Integer) session.getAttribute("userId");

        // 파일 저장 처리
        String imageUrl = FileManager.saveFile(userId, file);

        // 결과 반환
        Map<String, String> resultMap = new HashMap<>();
        if (imageUrl != null) {
            resultMap.put("result", "success");
            resultMap.put("imageUrl", imageUrl);
            return ResponseEntity.ok(resultMap);
        } else {
            resultMap.put("result", "fail");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }
    
    
    @PostMapping("/update")
    public Map<String, String> updatePost(@RequestBody Map<String, Object> requestData) {
        String postIdString = (String) requestData.get("postId");
        if (postIdString == null || postIdString.isEmpty()) {
            throw new IllegalArgumentException("postId must not be null or empty");
        }

        int postId = Integer.parseInt(postIdString); // String에서 Integer로 변환

        String title = (String) requestData.get("title");
        String contents = (String) requestData.get("contents");
        String imagePath = (String) requestData.get("imagePath");

        // 게시물 업데이트
        Post existingPost = postService.getPostById(postId);
        if (existingPost == null) {
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("result", "fail");
            resultMap.put("message", "게시물이 존재하지 않습니다.");
            return resultMap;
        }

        postService.updatePost(postId, title, contents, imagePath);

        // 성공 응답
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }



    
    @PostMapping("/delete")
    public Map<String, String> deletePost(
            @RequestParam("postId") int postId,
            HttpSession session) {
        
        // 로그인된 사용자 정보 가져오기 (세션에서 userId 추출)
        int userId = (Integer) session.getAttribute("userId");

        // 게시글 삭제 처리
        boolean isDeleted = postService.deletePost(postId, userId);

        // 응답 맵 구성
        Map<String, String> resultMap = new HashMap<>();
        if (isDeleted) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }
        return resultMap; // 삭제 결과 반환
    }



}


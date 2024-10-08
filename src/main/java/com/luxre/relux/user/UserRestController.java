package com.luxre.relux.user;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxre.relux.user.domain.User;
import com.luxre.relux.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			, @RequestParam("email") String email
			) {
		
		int count = userService.addUser(loginId, password, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count ==1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	// 중복확인 
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(
	        @RequestParam("loginId") String loginId) {
	    boolean isDuplicate = userService.isDuplicateId(loginId);
	    
	    Map<String, Boolean> resultMap = new HashMap<>();
	    resultMap.put("isDuplicate", isDuplicate);
	    
	    return resultMap;
	}
	
	// 로그인 
	@PostMapping("/login")
	public Map<String, String> login (
		@RequestParam("loginId") String loginId,
		@RequestParam("password") String password,
		HttpServletRequest request) {
		
		// 사용자 조회
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user == null) {
			// 로그인 실패
			resultMap.put("result", "fail");
		} else {
			// 로그인 성공
	        resultMap.put("result", "success");
	        resultMap.put("userName", user.getName()); // 사용자 이름 추가
	        
	        // HttpSession 객체를 가져와서 사용자 정보를 저장
	        HttpSession session = request.getSession();
	        session.setAttribute("userId", user.getId());
	        session.setAttribute("userName", user.getName());
		}
		
		return resultMap;
	}
	
	@GetMapping("/session-check")
	public Map<String, Object> checkSession(HttpServletRequest request) {
	    Map<String, Object> resultMap = new HashMap<>();
	    HttpSession session = request.getSession(false);
	    
	    if (session != null && session.getAttribute("userId") != null) {
	        resultMap.put("isLoggedIn", true);
	        resultMap.put("userName", session.getAttribute("userName")); // 세션에서 사용자 이름 가져오기
	    } else {
	        resultMap.put("isLoggedIn", false);
	    }
	    
	    return resultMap;
	}

}

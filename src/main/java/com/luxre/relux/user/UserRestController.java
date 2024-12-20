package com.luxre.relux.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
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
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			, @RequestParam("email") String email
			) {
		
		// 중복 아이디 확인
	    if (userService.isDuplicateId(loginId)) {
	        Map<String, String> resultMap = new HashMap<>();
	        resultMap.put("result", "duplicate");
	        return resultMap;
	    }
		
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
	        
	        // admin 계정 확인
	        if ("admin".equals(loginId) && "admin1234".equals(password)) {
	            resultMap.put("redirect", "/admin/banner/list-view"); 
	        } else {
	            resultMap.put("redirect", "/main/list-view"); 
	        }
		}
		
		return resultMap;
	}
	
	 @GetMapping("/session-check")
	    public Map<String, Object> checkSession(HttpSession session) {
	        Map<String, Object> response = new HashMap<>();
	        Integer userId = (Integer) session.getAttribute("userId");

	        if (userId != null) {
	            // 로그인 상태
	            response.put("isLoggedIn", true);
	            User user = userService.getUserById(userId);
	            response.put("userName", user.getName());
	        } else {
	            // 비로그인 상태
	            response.put("isLoggedIn", false);
	        }

	        return response;
	    }
	 
	 
	 @PostMapping("/find-id")
	 public String findId(@RequestParam("email") String email, Model model) {
	     try {
	         userService.sendId(email);
	         return "redirect:/user/idfind-successview"; 
	     } catch (IllegalArgumentException e) {
	         model.addAttribute("result", "fail");
	         model.addAttribute("errorMessage", e.getMessage());
	         return "/user/idfind-view"; 
	     }
	 }
	 
	 @PostMapping("/find-password")
	 public String findPassword(@RequestParam("email") String email, Model model) {
	     try {
	         userService.sendPassword(email);
	         return "redirect:/user/pwfind-successview"; 
	     } catch (IllegalArgumentException e) {
	         model.addAttribute("result", "fail");
	         model.addAttribute("errorMessage", e.getMessage());
	         return "/user/pwfind-view"; 
	     }
	 }

}



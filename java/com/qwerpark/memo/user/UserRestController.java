package com.qwerpark.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qwerpark.memo.user.domain.User;
import com.qwerpark.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private UserService userService;
	
//	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId")String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email) {
		
		int count = userService.addUser(loginId, password, name, email);
		
		Map<String , String> resultMap = new HashMap<>();
		
		if(count ==1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam("loginId") String loginId) {
		boolean isDuplicate = userService.isDuplicateId(loginId);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		resultMap.put("isDuplicate", isDuplicate);
		
		return resultMap;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
	        @RequestParam("loginId") String loginId,
	        @RequestParam("password") String password,
	        HttpServletRequest request) {

	    // 사용자를 조회
	    User user = userService.getUser(loginId, password);

	    // 결과를 저장할 Map
	    Map<String, String> resultMap = new HashMap<>();

	    if (user == null) {
	        // 로그인 실패
	        resultMap.put("result", "fail");
	    } else {
	        // 로그인 성공
	        resultMap.put("result", "success");

	        // HttpSession 객체를 가져와서 사용자 정보를 저장
	        HttpSession session = request.getSession();
	        session.setAttribute("userId", user.getId());
	        session.setAttribute("userName", user.getName());
	    }

	    return resultMap;
	}

}

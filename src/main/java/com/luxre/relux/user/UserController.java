package com.luxre.relux.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	
	// 회원가입 이동
	@GetMapping("/join-view")
	public String inputJoin() {	
		return "user/join";
	}
	
	// 로그인 이동
	@GetMapping("/login-view")
	public String inputLogin() {
		return "user/login";
	}
	
	// 아이다찾기 이동
	@GetMapping("/idfind-view")
	public String idFind() {
		return "user/idfind";
	}
	
	// 비밀번호 찾기 이동
	@GetMapping("/pwfind-view")
	public String pwFind() {
		return "user/pwfind";
	}
	
	// 아이디 찾기
	@GetMapping("/idfind-successview")
	public String idFindSuccess() {
		return "user/idfindsuccess";
	}
	
	// 비밀번호 찾기
	@GetMapping("/pwfind-successview")
	public String pwFindSuccess() {
		return "user/pwfindsuccess";
	}
		

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/main/list-view";
	}
}

package com.luxre.relux.user.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luxre.relux.common.HashingEncoder;
import com.luxre.relux.user.domain.User;
import com.luxre.relux.user.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private HashingEncoder encoder;

	public UserService(UserRepository userRepository,
	                   @Qualifier("sha256Hashing") HashingEncoder encoder) {
	    this.userRepository = userRepository;
	    this.encoder = encoder;
	}

	// 중복 확인
	public boolean isDuplicateId(String loginId) {
	    int count = userRepository.selectCountByLoginId(loginId);
	    return count != 0;
	}

	// 회원가입
	public int addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
	    String encryptPassword = encoder.encode(password);
	    
	    return userRepository.insertUser(loginId, encryptPassword, name, email);
	}

	// 사용자 아이디 조회
	public User getUserById(int id) {
	    return userRepository.selectUserById(id);
	}

	// 로그인
	public User getUser(String loginId, String password) {
	    String encryptPassword = encoder.encode(password);
	    return userRepository.selectUser(loginId, encryptPassword);
	}
}

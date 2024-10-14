package com.luxre.relux.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.luxre.relux.user.domain.User;

@Mapper
public interface UserRepository {
	
	// 회원가입
	public int insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email);
	
	
	// 중복 확인
	public int selectCountByLoginId(@Param("loginId") String loginId);
	
	// 로그인
	 public User selectUser(
	    		@Param("loginId") String loginId
	    		,@Param("password") String password);
	
	// 사용자 조회
	public User selectUserById(@Param("id") int id);


}

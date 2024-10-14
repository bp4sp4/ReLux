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

    public boolean isDuplicateId(String loginId) {
        int count = userRepository.selectCountByLoginId(loginId);
        return count > 0; // count가 0보다 크면 중복
    }

    // 회원가입
    public int addUser(String loginId, String password, String name, String email) {
        // 중복된 ID 체크
        if (isDuplicateId(loginId)) {
            throw new IllegalArgumentException("이미 사용 중인 loginId입니다.");
        }

        String encryptPassword = encoder.encode(password);
        System.out.println("Adding user with loginId: " + loginId);
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

    // post 사용자 이름 가져오기
    public String getUserNameById(int userId) {
        // userId로 사용자 조회
        User user = userRepository.selectUserById(userId);
        // 임의 더미데이터를 넣었기때문에 예외처리
        if (user != null) {
            return user.getName();
        } else {
            return "Unknown";
        }
    }
}

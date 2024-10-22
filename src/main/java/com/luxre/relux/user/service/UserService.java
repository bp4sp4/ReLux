package com.luxre.relux.user.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.luxre.relux.common.HashingEncoder;
import com.luxre.relux.user.domain.User;
import com.luxre.relux.user.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private HashingEncoder encoder;
    private JavaMailSender mailSender;

    public UserService(UserRepository userRepository,
                       @Qualifier("sha256Hashing") HashingEncoder encoder, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.mailSender = mailSender;
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
    
    public void sendId(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("리럭스 : 로그인 아이디를 메일로 보내드려요.");
            message.setText("당신의 아이디는: " + user.getLoginId());
            mailSender.send(message);
        } else {
            throw new IllegalArgumentException("이메일에 해당하는 사용자 아이디가 없습니다.");
        }
    }
    
    public void sendPassword(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("이메일에 해당하는 사용자가 없습니다.");
        }

        String temporaryPassword = RandomPassword();
        String encryptedPassword = encoder.encode(temporaryPassword);
        
        // DB에 임시 비밀번호 업데이트
        userRepository.updatePassword(user.getId(), encryptedPassword);

        // 이메일로 전송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("리럭스 : 임시 비밀번호 발송");
        message.setText("당신의 임시 비밀번호는: " + temporaryPassword);
        mailSender.send(message);
    }

    // 임시 비밀번호 생성
    private String RandomPassword() {
        int length = 8; //  8자리 임시 비밀번호 길이
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder temporaryPassword = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            temporaryPassword.append(chars.charAt(random.nextInt(chars.length())));
        }
        return temporaryPassword.toString();
    }
}

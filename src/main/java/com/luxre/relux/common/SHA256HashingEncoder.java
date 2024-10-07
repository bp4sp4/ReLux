package com.luxre.relux.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component("sha256Hashing")
public class SHA256HashingEncoder implements HashingEncoder {

    @Override
    public String encode(String message) {
        StringBuilder result = new StringBuilder();
        try {
            // SHA-256 알고리즘 사용
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = message.getBytes();

            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();

            // byte 배열을 16진수 문자열로 변환
            for (byte b : digest) {
                result.append(String.format("%02x", b & 0xff));
            }

        } catch (NoSuchAlgorithmException e) {
            return null;  // 혹은 적절한 예외 처리 로직 추가
        }
        return result.toString();
    }
}

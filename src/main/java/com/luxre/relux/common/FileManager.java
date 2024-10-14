package com.luxre.relux.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

public class FileManager {
    public static final String FILE_UPLOAD_PATH = "/Users/sanghunpark/Documents/SpringProject/Relux"; 

    public static String saveFile(int userId, MultipartFile file) {
        String directoryName = "/" + userId + "_" + System.currentTimeMillis();
        String directoryPath = FILE_UPLOAD_PATH + directoryName;

        File directory = new File(directoryPath);
        if (!directory.mkdir()) {
            // 폴더 생성 실패
            return null;
        }

        // 파일 저장
        String filePath = directoryPath + "/" + file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
        } catch (IOException e) {
            // 파일 저장 실패
            return null;
        }

        // 저장된 파일의 URL path 생성
        return "/images" + directoryName + "/" + file.getOriginalFilename();
    }

    public static boolean removeFile(String filePath) {
        if (filePath == null) {
            return false;
        }

        String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
        Path path = Paths.get(fullFilePath);
        Path directoryPath = path.getParent();

        // 파일과 폴더 존재 확인
        if (Files.exists(path) && Files.exists(directoryPath)) {
            try {
                Files.delete(path);
                Files.delete(directoryPath);
            } catch (IOException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}

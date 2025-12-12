package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Value;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

// [12주차] 포트폴리오의 '연락처 폼'을 통해 들어온 데이터를 서버에 파일 형태로 업로드(저장)하는 기능을 담당하는 컨트롤러

@Controller
public class FileController {

    // [12주차]
    @Value("${spring.servlet.multipart.location}") // properties 등록된 설정(경로) 주입
    private String uploadFolder;

    // [12주차]
    @PostMapping("/upload-email")
    public String uploadEmail( // 이메일, 제목, 메시지를 전달받음
            @RequestParam("email") String email, @RequestParam("subject") String subject,
            @RequestParam("message") String message, RedirectAttributes redirectAttributes) {
        try {
            Path uploadPath = Paths.get(uploadFolder).toAbsolutePath();
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 12주차 연습문제(파일 덮어쓰기 방지 로직)
            // [수정] 파일 이름 생성 로직 변경
            String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
            // 현재 시간을 "yyyyMMdd_HHmmss" 형식의 문자열로 만듭니다. (예: 20251207_093100)
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            // 파일 이름을 "이메일_타임스탬프.txt" 형식으로 조합하여 고유하게 만듭니다.
            String uniqueFileName = sanitizedEmail + "_" + timestamp + ".txt";
            Path filePath = uploadPath.resolve(uniqueFileName);

            System.out.println("File path: " + filePath); // 디버깅용 출력
            // [12주차]
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(filePath.toFile()), StandardCharsets.UTF_8))) {
                writer.write("메일 제목: " + subject);
                writer.newLine();
                writer.write("요청 메시지:");
                writer.newLine();
                writer.write(message);
            }
            // 리다이렉트 페이지에 성공 메시지를 일회성으로 전달
            redirectAttributes.addFlashAttribute("message", "메일 내용이 성공적으로 업로드되었습니다!");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "업로드 중 오류가 발생했습니다.");
            return "/error_page/article_error"; // [12주차 연습문제] 파일 입출력 에러 처리
        }
        return "upload_end"; // .html 파일 연동
    }
}

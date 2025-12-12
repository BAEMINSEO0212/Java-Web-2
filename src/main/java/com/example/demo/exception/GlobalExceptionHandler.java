package com.example.demo.exception; // 본인의 패키지 경로에 맞게 확인하세요.

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// [6주차 연습문제] 프로젝트 전역에서 발생하는 예외(Exception)를 통합적으로 처리하는 클래스.
@ControllerAdvice
public class GlobalExceptionHandler {

    // [6주차 연습문제] 특정 예외 타입을 지정하여 처리하는 메소드.
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(Model model) {

        // 에러 페이지에 추가적인 정보를 전달하고 싶다면 모델에 담을 수 있습니다.
        model.addAttribute("errorMessage", "URL의 파라미터 타입이 잘못되었습니다.");

        // templates/error_page/type_mismatch_error.html 파일을 뷰로 사용합니다.
        return "/error_page/type_mismatch_error";
    }
}
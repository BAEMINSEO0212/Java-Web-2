package com.example.demo.exception; // 본인의 패키지 경로에 맞게 확인하세요.

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// @ControllerAdvice: 이 클래스가 프로젝트 전역의 예외를 처리하는 클래스임을 명시합니다.
@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler: 특정 예외가 발생했을 때 아래 메소드를 실행하도록 지정합니다.
    // 여기서는 MethodArgumentTypeMismatchException (타입 불일치 예외)를 잡아냅니다.
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(Model model) {

        // 에러 페이지에 추가적인 정보를 전달하고 싶다면 모델에 담을 수 있습니다.
        model.addAttribute("errorMessage", "URL의 파라미터 타입이 잘못되었습니다.");

        // 1단계에서 만든 새로운 에러 페이지의 경로를 반환합니다.
        return "/error_page/type_mismatch_error";
    }
}
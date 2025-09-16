package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model; // 추가된 부분

//import java.util.List; 2주차 과제 응용2 부분

@Controller
public class DemoController {

    // 2주차 설정 부분(index.html 연결)
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 2주차 추가 부분(hello.html 연결)
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "반갑습니다.");
        return "hello";
    }

    // 2주차 추가 부분(hello2.html 연결)
    @GetMapping("/hello2")
    public String hello2(Model model) {
        model.addAttribute("name", "배민서님.");
        model.addAttribute("hello", "반갑습니다.");
        model.addAttribute("today", "오늘.");
        model.addAttribute("weather", "날씨는.");
        model.addAttribute("comment", "매우 좋습니다.");
        return "hello2";
    }
    // 2주차 과제 응용2 부분(List 사용)
    /*
     * @GetMapping("/hello2")
     * public String hello2(Model model) {
     * // 여러 개의 문자열 데이터를 하나의 List로 만듭니다.
     * List<String> messageList = List.of(
     * "홍길동님.",
     * "방갑습니다.",
     * "오늘.",
     * "날씨는.",
     * "매우 좋습니다.");
     * // List 자체를 "messages"라는 이름으로 Model에 담습니다.
     * model.addAttribute("messages", messageList);
     * return "hello2";
     * }
     */

}
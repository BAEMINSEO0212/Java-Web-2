package com.example.demo.controller;

import com.example.demo.model.domain.TestDB;
import com.example.demo.model.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
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

    // 3주차 추가 부분(about_detailed.html 연결)

    @GetMapping("/about_detailed")
    public String about() {
        return "about_detailed";
    }

    @GetMapping("/test1")
    public String thymeleaf_test1(Model model) {
        model.addAttribute("data1", "<h2> 반갑습니다 </h2>");
        model.addAttribute("data2", "태그의 속성 값");
        model.addAttribute("link", 01);
        model.addAttribute("name", "홍길동");
        model.addAttribute("para1", "001");
        model.addAttribute("para2", 002);
        return "thymeleaf_test1";
    }

    @Autowired
    TestService testService; // DemoController 클래스 아래 객체 생성
    // 하단에 맵핑 이어서 추가

    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        TestDB test = testService.findByName("홍길동");
        model.addAttribute("data4", test);
        System.out.println("데이터 출력 디버그 : " + test);
        return "testdb";
    }

}
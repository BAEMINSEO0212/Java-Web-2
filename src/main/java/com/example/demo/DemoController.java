package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 이 클래스가 컨트롤러 역할을 한다고 Spring에게 알려줍니다.
public class DemoController {

    @GetMapping("/") // 사용자가 웹 브라우저에 "/" (기본 주소)를 입력하면 아래의 메소드를 실행합니다.
    public String home() {
        return "index"; // templates 폴더에 있는 index.html 파일을 찾아서 사용자에게 보여줍니다.
    }
}
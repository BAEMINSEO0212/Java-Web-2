package com.example.demo;

import org.springframework.boot.SpringApplication; // 스프링 핵심 클래스
import org.springframework.boot.autoconfigure.SpringBootApplication; // 스프링 부트 자동 설정 클래스

// 이 프로젝트의 시작점 역할을 하는 메인 클래스.
@SpringBootApplication
public class DemoApplication {

	// 자바 애플리케이션의 메인 메소드.
	public static void main(String[] args) { // 메인 메서드(프로그램 시작점)
		SpringApplication.run(DemoApplication.class, args); // run 메서드로 실행
	}
}
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.domain.Member;
import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.service.MemberService;

// [추가] 세션 및 쿠키 처리를 위한 import
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/join_new")
    public String join_new() {
        return "join_new";
    }

    @PostMapping("/api/members")
    public String addmembers(@Valid @ModelAttribute AddMemberRequest request) {
        memberService.saveMember(request);
        return "join_end";
    }

    @GetMapping("/member_login")
    public String member_login() {
        return "login";
    }

    /**
     * [11주차 17페이지 핵심]
     * 로그인 요청 시, 기존에 다른 사용자의 세션이 남아있다면 파기하고
     * 새로운 세션을 생성하여 중복 로그인을 방지하고 단일 사용자만 로그인되도록 처리합니다.
     */
    @PostMapping("/api/login_check")
    public String checkMembers(@ModelAttribute AddMemberRequest request, Model model,
            HttpServletRequest request2, HttpServletResponse response) { // [수정] HttpServletRequest/Response 추가

        // --- 17페이지 추가된 내용 시작 ---
        HttpSession session = request2.getSession(false); // 기존 세션 가져오기 (없으면 null 반환)
        if (session != null) {
            session.invalidate(); // 기존 세션 무효화
            Cookie cookie = new Cookie("JSESSIONID", null); // JSESSIONID 쿠키 초기화
            cookie.setPath("/"); // 쿠키 경로 설정
            cookie.setMaxAge(0); // 쿠키 삭제 (만료 시간을 0으로 설정)
            response.addCookie(cookie); // 응답에 쿠키를 추가하여 클라이언트에 전달
        }
        // 새로운 세션 생성
        session = request2.getSession(true);
        // --- 17페이지 추가된 내용 끝 ---

        try {
            Member member = memberService.loginCheck(request.getEmail(), request.getPassword());

            // 새로운 세션에 현재 로그인한 사용자 정보 저장
            session.setAttribute("userId", member.getId().toString());
            session.setAttribute("email", member.getEmail());

            return "redirect:/board_list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/api/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/member_login";
    }
}
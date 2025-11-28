package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.service.AddMemberRequest;

import com.example.demo.model.domain.Member;
import com.example.demo.model.service.MemberService;
import jakarta.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/join_new") // 회원 가입 페이지 연결
    public String join_new() {
        return "join_new"; // .HTML 연결
    }

    @GetMapping("/member_login") // 로그인 페이지 연결
    public String member_login() {
        return "login"; // .HTML 연결
    }

    @PostMapping("/api/login_check") // 로그인(아이디, 패스워드) 체크
    public String checkMembers(@ModelAttribute AddMemberRequest request, Model model) {
        try {
            Member member = memberService.loginCheck(request.getEmail(), request.getPassword()); // 패스워드 반환
            model.addAttribute("member", member); // 로그인 성공 시 회원 정보 전달
            return "redirect:/board_list"; // 로그인 성공 후 이동할 페이지
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // 에러 메시지 전달
            return "login"; // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }

    @PostMapping("/api/members")
    public String addmembers(@Valid @ModelAttribute AddMemberRequest request, BindingResult bindingResult,
            Model model) {

        // [핵심] 검증 오류가 있는지 확인
        if (bindingResult.hasErrors()) {
            // 오류가 있다면, 오류 메시지를 Model에 담아서
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "Error", error.getDefaultMessage());
            }
            // 다시 회원가입 페이지(join_new.html)로 돌려보냄
            return "join_new";
        }

        // 검증을 통과해야만 아래 코드가 실행됨
        memberService.saveMember(request);
        return "join_end";
    }

}
package com.example.demo.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.model.domain.Member;
import com.example.demo.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

// [해결] @NonNull 어노테이션을 import 합니다.

// [10주차] 회원(Member) 관련 핵심 비즈니스 로직(회원가입, 로그인)을 처리하는 서비스 클래스.
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // 스프링 버전 5 이후, 단방향 해싱 알고리즘 지원

    // [10주차] 회원가입 시 이메일 중복 여부를 검사하는 내부 메소드.
    private void validateDuplicateMember(AddMemberRequest request) { // [해결] request 파라미터에 @NonNull 추가

        Member findMember = memberRepository.findByEmail(request.getEmail()); // 이메일 존재 유무
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다."); // 예외처리
        }
    }

    // [10주차] 새로운 회원을 DB에 저장합니다. (회원가입 기능)
    @SuppressWarnings("null") // [해결] 이 메소드에서 발생하는 null 관련 경고를 무시하도록 설정
    public Member saveMember(@Valid AddMemberRequest request) {
        validateDuplicateMember(request); // 1. 이메일 중복 검사
        // 2. 비밀번호를 BCrypt 알고리즘으로 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);
        // 3. DTO를 Entity로 변환하여 DB에 저장
        return memberRepository.save(request.toEntity());
    }

    // [11주차] 이메일과 비밀번호를 이용해 로그인을 검증하는 메소드.
    public Member loginCheck(String email, String rawPassword) {
        // 1. 이메일로 회원 정보 조회
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }

        // 2. 입력된 비밀번호와 DB의 암호화된 비밀번호를 비교 (matches 메소드 사용)
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            // 일치하지 않으면 예외를 발생시킵니다.
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 인증 성공 시 회원 객체 반환
        return member;
    }

}

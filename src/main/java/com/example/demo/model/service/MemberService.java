package com.example.demo.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.domain.Member;
import com.example.demo.model.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // 트랜잭션 처리(클래스 내 모든 메소드 대상)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // 스프링 버전 5 이후, 단방향 해싱 알고리즘 지원

    private void validateDuplicateMember(AddMemberRequest request) {
        Member findMember = memberRepository.findByEmail(request.getEmail()); // 이메일 존재 유무
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다."); // 예외처리
        }
    }

    public Member saveMember(AddMemberRequest request) {
        validateDuplicateMember(request); // 이메일 체크
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword); // 암호화된 비밀번호 설정
        return memberRepository.save(request.toEntity());
    }

    public Member loginCheck(String email, String rawPassword) {
        // 1. 이메일을 이용해 DB에서 회원 정보를 조회합니다.
        Member member = memberRepository.findByEmail(email);

        // 2. 조회된 회원이 없으면(null), 예외를 발생시킵니다.
        if (member == null) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }

        // 3. 입력된 비밀번호(rawPassword)와 DB에 저장된 암호화된 비밀번호가 일치하는지 확인합니다.
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            // 일치하지 않으면 예외를 발생시킵니다.
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 4. 모든 인증을 통과하면, 회원 정보를 담은 member 객체를 반환합니다.
        return member;
    }

}

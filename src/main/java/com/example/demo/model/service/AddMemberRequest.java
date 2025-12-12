package com.example.demo.model.service;

import com.example.demo.model.domain.Member;
import lombok.Data;

// [10주차 연습문제] 값 검증(Validation)을 위한 어노테이션 import
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

// [10주차] 회원가입(Sign-up) 요청 데이터를 전달하기 위해 생성된 DTO.

@Data
public class AddMemberRequest {
    // [10주차 연습문제] 이름 필드에 대한 검증 규칙
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "이름은 한글 또는 영문만 사용 가능합니다.")
    private String name;
    // [10주차 연습문제] 이메일 필드에 대한 검증 규칙
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;
    // [10주차 연습문제] 비밀번호 필드에 대한 검증 규칙
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$", message = "비밀번호는 영문과 숫자를 포함하여 8자 이상이어야 합니다.")
    private String password;
    // [10주차 연습문제] 나이 필드에 대한 검증 규칙
    @Min(value = 19, message = "나이는 19세 이상이어야 합니다.")
    @Max(value = 90, message = "나이는 90세 이하이어야 합니다.")
    private String age;
    // [10주차 연습문제] 휴대전화 번호 필드
    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String mobile;
    // [10주차 연습문제] 주소 필드
    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    private String address;

    // [10주차] 이 DTO 객체를 Member 엔티티 객체로 변환하는 메소드.
    public Member toEntity() { // Member 생성자를 통해 객체 생성
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .age(age)
                .mobile(mobile)
                .address(address)
                .build();
    }
}
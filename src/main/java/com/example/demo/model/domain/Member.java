package com.example.demo.model.domain;

import lombok.*;
import jakarta.persistence.*;

// [10주차] 회원가입 및 로그인 기능을 위해 생성한 엔티티 클래스.
@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    // [10주차] 회원의 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 1씩 증가
    @Column(name = "id", updatable = false) // 수정 x
    private Long id;
    // [10주차] 회원의 이름
    @Column(name = "name", nullable = false) // null x
    private String name = "";
    // [10주차] 회원의 이메일
    @Column(name = "email", unique = true, nullable = false) // unique 중복 x
    private String email = "";
    // [10주차] 회원의 비밀번호
    @Column(name = "password", nullable = false)
    private String password = "";
    // [10주차] 회원의 나이
    @Column(name = "age", nullable = false)
    private String age = "";
    // [10주차] 회원의 휴대전화 번호
    @Column(name = "mobile", nullable = false)
    private String mobile = "";
    // [10주차] 회원의 주소
    @Column(name = "address", nullable = false)
    private String address = "";

    // [10주차] 빌더 패턴을 사용하여 객체를 생성하기 위한 생성자.
    @Builder
    public Member(String name, String email, String password, String age, String mobile, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
    }
}

package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.Data;

// [4주차] 스프링 부트와 데이터베이스(MySQL) 연동을 테스트하기 위해 생성한 엔티티 클래스.
@Entity // TestDB라는 객체와 DB 테이블을 매핑. JPA가 관리
@Table(name = "testdb") // 테이블 이름은 testdb
@Data // set/get/tostring 등 필수 어노테이션 자동 생성

public class TestDB {
    // [4주차] 테스트용 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // [4주차] 테스트용 사용자 이름
    @Column(nullable = true)
    private String name;
}
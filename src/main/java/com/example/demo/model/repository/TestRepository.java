package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository; // JPA 필수 등록
import org.springframework.stereotype.Repository; // 빈 등록
import com.example.demo.model.domain.TestDB; // 도메인 연동

//[4주차] TestDB 엔티티에 대한 데이터베이스 접근을 테스트하기 위해 생성한 리포지토리.
@Repository // 리포지토리 등록
public interface TestRepository extends JpaRepository<TestDB, Long> {
    // [4주차] JPA 쿼리 메소드를 사용하여 이름(name)으로 사용자를 조회하는 기능을 테스트.
    TestDB findByName(String name);
}
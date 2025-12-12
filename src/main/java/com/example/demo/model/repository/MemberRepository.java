package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.Member;

//[10주차] Member 엔티티에 대한 데이터베이스 접근(CRUD)을 처리하는 리포지토리 인터페이스.
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // [10주차] JPA 쿼리 메소드를 사용하여 이메일(email)로 회원을 조회하는 기능을 정의.
    Member findByEmail(String email);
}
package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.Article;

/**
 * [5주차] Article 엔티티에 대한 데이터베이스 접근(CRUD)을 처리하기 위해 생성한 리포지토리.
 * 
 * [현재 상태]
 * 7주차부터 `BoardRepository`를 사용하게 되면서, 이 리포지토리는 현재 사용되지 않습니다.
 * 초기 JPA 리포지토리 구현을 학습하기 위한 기록으로 남겨둡니다.
 */
@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
    // JpaRepository를 상속받았기 때문에,
    // save(), findById(), findAll(), deleteById() 등의 메소드가
    // 이미 구현되어 있는 것과 같습니다.
}
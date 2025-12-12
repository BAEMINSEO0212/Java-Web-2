package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//[7주차] Board 엔티티에 대한 데이터베이스 접근(CRUD)을 처리하는 리포지토리 인터페이스.
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // [9주차] 게시판 검색 기능을 위해 추가된 JPA 쿼리 메소드.
    Page<Board> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
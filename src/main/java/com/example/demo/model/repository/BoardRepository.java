package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
// JpaRepository가 다루는 엔티티가 <Board, Long> 인지 다시 한번 확인!
public interface BoardRepository extends JpaRepository<Board, Long> {

    // ▼▼▼ [핵심] 검색 메소드를 BlogRepository가 아닌 여기에 추가해야 합니다! ▼▼▼
    Page<Board> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
package com.example.demo.model.domain;

import lombok.*;
import jakarta.persistence.*;

// [7주차] 기존 Article 엔티티를 확장하여 생성한 핵심 엔티티 클래스.

@Getter
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    // [7주차] 게시글의 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    // [7주차] 게시글의 제목
    @Column(name = "title", nullable = false)
    private String title; // 기본값 ""는 생성자에서 처리하므로 제거해도 괜찮습니다.
    // [7주차] 게시글의 내용
    @Column(name = "content", nullable = false)
    private String content;

    // [7주차] 게시글의 작성자
    @Column(name = "user", nullable = false)
    private String user;
    // [7주차] 게시글의 작성일
    @Column(name = "newdate", nullable = false)
    private String newdate;
    // [7주차] 게시글의 조회수
    @Column(name = "count", nullable = false)
    private String count;
    // [7주차] 게시글의 좋아요 수
    @Column(name = "likec", nullable = false)
    private String likec;

    // [7주차] 빌더 패턴을 사용하여 객체를 생성하기 위한 생성자.
    @Builder
    public Board(String title, String content, String user, String newdate, String count, String likec) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likec = likec;
    }

    /**
     * [7주차 연습문제] 게시글의 내용을 수정하기 위한 메소드.
     * [11주차 연습문제] 수정 시 폼에 없는 데이터(user, newdate 등)가 null이 되지 않도록
     * Service 계층에서 기존 값을 유지하는 로직과 함께 사용됩니다.
     */
    public void update(String title, String content, String user, String newdate, String count, String likec) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likec = likec;
    }
}
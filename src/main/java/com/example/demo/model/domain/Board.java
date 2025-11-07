package com.example.demo.model.domain;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; // 기본값 ""는 생성자에서 처리하므로 제거해도 괜찮습니다.

    @Column(name = "content", nullable = false)
    private String content;

    // [수정] 7주차 강의 내용에 따라 새로운 필드를 추가
    @Column(name = "user", nullable = false)
    private String user;

    @Column(name = "newdate", nullable = false)
    private String newdate;

    @Column(name = "count", nullable = false)
    private String count;

    @Column(name = "likec", nullable = false)
    private String likec;

    @Builder // 생성자에 빌더 패턴 적용(불변성)
    // [수정] 생성자에 새로운 필드를 모두 포함시킵니다.
    public Board(String title, String content, String user, String newdate, String count, String likec) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likec = likec;
    }

    // [수정] update 메소드에도 새로운 필드를 반영합니다.
    public void update(String title, String content, String user, String newdate, String count, String likec) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likec = likec;
    }
}
package com.example.demo.model.domain;

import lombok.*; // 어노테이션 자동 생성
import jakarta.persistence.*; // 기존 javax 후속 버전

// [5주차] JPA를 학습하기 위해 처음으로 생성한 엔티티 클래스.
/* [현재 상태]
 * 7주차부터는 이 클래스를 확장한 `Board` 엔티티를 주로 사용하고 있으므로,
 * 이 클래스는 현재 직접적으로 사용되지 않습니다. 초기 학습용 기록으로 남겨둡니다.
 */
@Getter // setter는 없음(무분별한 변경 x)
@Entity // 아래 객체와 DB 테이블을 매핑. JPA가 관리
@Table(name = "article") // 테이블 이름을 지정. 없는 경우 클래스이름으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성자 접근 방지

public class Article {
    // [5주차] 게시글의 고유 ID (Primary Key)
    @Id // 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 1씩 증가
    @Column(name = "id", updatable = false) // 수정 x
    private Long id;

    // [5주차] 게시글의 제목
    @Column(name = "title", nullable = false) // null x
    private String title = "";

    // [5주차] 게시글의 내용
    @Column(name = "content", nullable = false)
    private String content = "";

    // [5주차] 빌더 패턴을 사용하여 객체를 생성하기 위한 생성자.
    @Builder // 생성자에 빌더 패턴 적용(불변성)
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // [6주차] 게시글의 내용을 수정하기 위한 메소드.
    public void update(String title, String content) { // 현재 객체 상태 업데이트
        this.title = title;
        this.content = content;
    }
}

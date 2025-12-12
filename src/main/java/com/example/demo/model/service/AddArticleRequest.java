package com.example.demo.model.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.model.domain.Board;
import lombok.AllArgsConstructor;

/**
 * [5주차] 게시글(Article) 생성 요청을 받을 때 사용하기 위해 처음 생성된 DTO(Data Transfer Object).
 * [7주차] Board 엔티티의 필드가 확장됨에 따라, 글 수정(Update) 시 데이터를 전달하는 역할로도 사용되었습니다.
 * [9주차] Board 엔티티로 변환하는 toEntity() 메소드가 추가되어 글 생성(Create) 로직이 완성되었습니다.
 * 
 * DTO의 역할:
 * 1. 웹 계층(Controller)에서 뷰(HTML Form)로부터 전송된 데이터를 받습니다.
 * 2. 받은 데이터를 서비스 계층(Service)으로 안전하게 전달하는 '데이터 상자' 역할을 합니다.
 * 3. Entity 객체를 직접 노출하지 않아 시스템의 보안과 안정성을 높입니다.
 * 
 * @Data: Lombok 어노테이션으로, 모든 필드에 대한 Getter, Setter, toString 등을 자동으로 생성합니다.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {

    // [5주차] 글 제목과 내용을 전달받기 위한 필드
    private String title;
    private String content;
    // [7주차 연습문제] 글 수정 기능을 구현하면서 Board의 모든 필드를 포함하도록 확장되었습니다. private String user;
    private String newdate;
    private String count;
    private String likec;

    // [9주차] DTO 객체를 Board 엔티티 객체로 변환하는 메소드.
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .user("GUEST") // 임시 사용자
                .newdate(java.time.LocalDate.now().toString()) // 임시 날짜
                .count("0")
                .likec("0")
                .build();
    }

}

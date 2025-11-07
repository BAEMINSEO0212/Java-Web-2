// AddArticleRequest.java
package com.example.demo.model.service;

//import com.example.demo.model.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    // [연습문제] Board의 다른 필드들도 추가합니다.
    private String user;
    private String newdate;
    private String count;
    private String likec;

    // toEntity() 메소드는 지금 사용하지 않으므로 주석 처리하거나 그대로 둬도 됩니다.
}

// 기존 내용

/*
 * package com.example.demo.model.service;
 * 
 * import lombok.*; // 어노테이션 자동 생성
 * import com.example.demo.model.domain.Article;
 * 
 * @NoArgsConstructor // 기본 생성자 추가
 * 
 * @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
 * 
 * @Data // getter, setter, toString, equals 등 자동 생성
 * public class AddArticleRequest {
 * private String title;
 * private String content;
 * 
 * public Article toEntity() { // Article 객체 생성
 * return Article.builder()
 * .title(title)
 * .content(content)
 * .build();
 * }
 * }
 */
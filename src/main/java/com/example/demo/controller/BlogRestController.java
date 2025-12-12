package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

// [5주차] REST API 방식으로 Article 게시판의 CRUD를 처리하기 위해 만들었던 컨트롤러.
//import com.example.demo.model.domain.Article;
//import com.example.demo.model.service.AddArticleRequest;
//import com.example.demo.model.service.BlogService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

/* [현재 상태]
 * 7주차 이후부터는 Board 게시판을 사용하고, 모든 요청을 `BlogController`에서 처리하고 있으므로
 * 이 컨트롤러는 현재 사용되지 않습니다. 학습용 기록으로 남겨둡니다.
 */

@RequiredArgsConstructor
@RestController // @Controller + @ResponseBody

public class BlogRestController {
    // private final BlogService blogService;
    /*
     * [5주차] Article 게시글을 생성(Create)하는 POST 요청을 처리했던 메소드
     * 
     * @PostMapping("/api/articles") // post 요청
     * public ResponseEntity<Article> addArticle(@ModelAttribute AddArticleRequest
     * request) { // 아직 없음(에러)
     * Article saveArticle = blogService.save(request); // 게시글 저장
     * return ResponseEntity.status(HttpStatus.CREATED) // 상태 코드 및 게시글 정보 반환
     * .body(saveArticle);
     * }
     */
}
package com.example.demo.controller;

import java.util.Optional;
import java.util.List;
import com.example.demo.model.domain.Article;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.ui.Model; // 추가된 부분

//import java.util.List; 2주차 과제 응용2 부분

@Controller
public class BlogController {

    @Autowired
    BlogService blogService; // DemoController 클래스 아래 객체 생성
    // 하단에 맵핑 이어서 추가

    @GetMapping("/article_list") // 게시판 링크 지정
    public String article_list(Model model) {
        List<Article> list = blogService.findAll(); // 게시판 리스트
        model.addAttribute("articles", list); // 모델에 추가
        return "article_list"; // .HTML 연결
    }

    @PostMapping("/api/articles")
    public String addArticle(AddArticleRequest request) {
        blogService.save(request); // 폼에서 전송된 데이터(request)를 DB에 저장합니다.
        return "redirect:/article_list"; // 저장 후, 게시글 목록 페이지로 다시 접속하라고 브라우저에 명령합니다.
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
        // 아무 작업도 하지 않음
    }

    @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    public String article_edit(Model model, @PathVariable Long id) {
        Optional<Article> list = blogService.findById(id); // 선택한 게시판 글
        if (list.isPresent()) {
            model.addAttribute("article", list.get()); // 존재하면 Article 객체를 모델에 추가
            return "article_edit"; // 정상적인 경우, 수정 페이지로
        } else {
            // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결
        }
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/article_list"; // 글 수정 이후 .html 연결
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/article_list";
    }

}
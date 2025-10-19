package com.example.demo.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Article;
import com.example.demo.model.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 생성자 자동 생성(부분)
public class BlogService {

    @Autowired // 객체 주입 자동화, 생성자 1개면 생략 가능
    private final BlogRepository blogRepository; // 리포지토리 선언

    public List<Article> findAll() { // 게시판 전체 목록 조회
        return blogRepository.findAll();
    }

    public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
        return blogRepository.findById(id);
    }

    public Article save(AddArticleRequest request) {
        // DTO가 없는 경우 이곳에 직접 구현 가능
        // public ResponseEntity<Article> addArticle(@RequestParam String title,
        // @RequestParam String content) {
        // Article article = Article.builder()
        // .title(title)
        // .content(content)
        // .build();
        return blogRepository.save(request.toEntity());

    }

    public void update(Long id, AddArticleRequest request) {
        Optional<Article> optionalArticle = blogRepository.findById(id); // 1. id로 기존 게시글을 조회합니다.

        optionalArticle.ifPresent(article -> { // 2. 게시글이 존재하면
            article.update(request.getTitle(), request.getContent()); // 3. 엔티티의 update 메소드를 이용해 제목과 내용을 수정하고
            blogRepository.save(article); // 4. 수정한 객체를 데이터베이스에 다시 저장합니다.
        });
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

}

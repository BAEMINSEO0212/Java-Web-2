package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board; // 추가된 임포트
//import com.example.demo.model.repository.BlogRepository;
import com.example.demo.model.repository.BoardRepository; // 추가된 임포트
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자 자동 생성(부분)
public class BlogService {

    @Autowired // 객체 주입 자동화, 생성자 1개면 생략 가능
    // private final BlogRepository blogRepository; // 기존 리포지토리 주석 처리
    private final BoardRepository blogRepository; // 새로운 리포지토리 선언 (이름은 그대로 두고 타입만 BoardRepository로 변경)

    // 기존 findAll 주석 처리
    /*
     * public List<Article> findAll() { // 게시판 전체 목록 조회
     * return blogRepository.findAll();
     * }
     */

    // 새로운 findAll 추가 (Board 사용)
    public List<Board> findAll() { // 게시판 전체 목록 조회
        return blogRepository.findAll();
    }

    // 기존 findById 주석 처리
    /*
     * public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
     * return blogRepository.findById(id);
     * }
     */

    // 새로운 findById 추가 (Board 사용)
    public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
        return blogRepository.findById(id);
    }

    // 아직 Board용으로 수정되지 않은 아래 메소드들은 임시로 주석 처리합니다.
    /*
     * public Article save(AddArticleRequest request) {
     * // DTO가 없는 경우 이곳에 직접 구현 가능
     * // public ResponseEntity<Article> addArticle(@RequestParam String title,
     * // @RequestParam String content) {
     * // Article article = Article.builder()
     * // .title(title)
     * // .content(content)
     * // .build();
     * return blogRepository.save(request.toEntity());
     * }
     * 
     * public void update(Long id, AddArticleRequest request) {
     * Optional<Article> optionalArticle = blogRepository.findById(id); // 1. id로 기존
     * 게시글을 조회합니다.
     * 
     * optionalArticle.ifPresent(article -> { // 2. 게시글이 존재하면
     * article.update(request.getTitle(), request.getContent()); // 3. 엔티티의 update
     * 메소드를 이용해 제목과 내용을 수정하고
     * blogRepository.save(article); // 4. 수정한 객체를 데이터베이스에 다시 저장합니다.
     * });
     * }
     * 
     * public void delete(Long id) {
     * blogRepository.deleteById(id);
     * }
     */
}
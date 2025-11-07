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
//import lombok.NonNull;

// 날짜 처리를 위한 import 구문
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void update(Long id, AddArticleRequest request) { // AddArticleRequest는 title과 content만 있다고 가정
        Optional<Board> optionalBoard = blogRepository.findById(id);

        optionalBoard.ifPresent(board -> {
            // [수정] Board.java에 title과 content만 수정하는 새로운 update 메소드를 만들거나,
            // 여기서 직접 값을 설정합니다.

            // --- 해결 방법 ---
            // 폼에서 넘어온 title과 content 값으로만 기존 board 객체의 내용을 업데이트합니다.
            board.update(
                    request.getTitle(), // 폼에서 새로 받은 제목
                    request.getContent(), // 폼에서 새로 받은 내용
                    board.getUser(), // DB에서 읽어온 기존 user 값
                    board.getNewdate(), // DB에서 읽어온 기존 newdate 값
                    board.getCount(), // DB에서 읽어온 기존 count 값
                    board.getLikec() // DB에서 읽어온 기존 likec 값
            );

            blogRepository.save(board);
        });
    }

    public void delete(Long id) {
        // 리포지토리가 기본으로 제공하는 deleteById 메소드를 호출하여 데이터를 삭제합니다.
        blogRepository.deleteById(id);
    }

    public Board save(AddArticleRequest request) {
        // 1. 폼에서 받은 title, content 외에 나머지 값들을 기본값으로 설정하여
        // 완전한 Board 객체를 생성합니다.
        Board newBoard = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user("GUEST") // 작성자는 'GUEST'로 고정
                .newdate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM월 dd일"))) // 현재 날짜를 "MM월 dd일" 형식으로 저장
                .count("0") // 조회수는 0
                .likec("0") // 좋아요도 0
                .build();

        // 2. 리포지토리를 통해 완성된 객체를 DB에 저장(save)합니다.
        return blogRepository.save(newBoard);
    }
    // ▲▲▲ save 메소드 추가 완료 ▲▲▲

    /*
     * public void delete(Long id) {
     * blogRepository.deleteById(id);
     * }
     */

}
package com.example.demo.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Board;
import com.example.demo.model.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

// [추가] 페이징 관련 클래스 import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class BlogService {

    @Autowired
    private final BoardRepository blogRepository;

    /**
     * [9주차] 페이징이 적용된 전체 게시글 조회
     */
    public Page<Board> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    /**
     * ID로 특정 게시글 하나를 조회
     */
    public Optional<Board> findById(Long id) {
        return blogRepository.findById(id);
    }

    // [9주차] 키워드를 포함한 게시글을 페이징하여 검색

    public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    /**
     * 새로운 게시글을 DB에 저장 (글쓰기 기능)
     * AddArticleRequest의 toEntity() 메소드를 사용
     */
    public Board save(AddArticleRequest request, String email) { // [수정] email 파라미터 추가
        return blogRepository.save(
                Board.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .user(email) // [수정] 'GUEST' 대신 파라미터로 받은 email 사용
                        .newdate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM월 dd일")))
                        .count("0")
                        .likec("0")
                        .build());
    }

    /**
     * 기존 게시글을 수정 (수정 기능)
     */
    public void update(Long id, AddArticleRequest request) {
        Optional<Board> optionalBoard = blogRepository.findById(id);
        optionalBoard.ifPresent(board -> {
            board.update(
                    request.getTitle(),
                    request.getContent(),
                    board.getUser(),
                    board.getNewdate(),
                    board.getCount(),
                    board.getLikec());
            blogRepository.save(board);
        });
    }

    /**
     * 특정 게시글을 삭제 (삭제 기능)
     */
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}
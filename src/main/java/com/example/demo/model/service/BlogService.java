package com.example.demo.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Board;
import com.example.demo.model.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// [해결] @NonNull 어노테이션을 import 합니다.
import lombok.NonNull;

// [7주차] 게시판(Board) 관련 핵심 비즈니스 로직을 처리하는 서비스 클래스.
@Service
@RequiredArgsConstructor
public class BlogService {

    @Autowired
    private final BoardRepository blogRepository;

    // [9주차] 페이징이 적용된 전체 게시글 목록을 조회합니다. (검색어 없는 경우)
    public Page<Board> findAll(@NonNull Pageable pageable) { // [해결] pageable 파라미터에 @NonNull 추가
        return blogRepository.findAll(pageable);
    }

    // [7주차] ID를 이용해 특정 게시글 하나를 조회합니다. (상세 보기 기능)
    public Optional<Board> findById(@NonNull Long id) { // [해결] pageable 파라미터에 @NonNull 추가
        return blogRepository.findById(id);
    }

    // [9주차] 제목에 특정 키워드가 포함된 게시글을 페이징하여 검색합니다.
    public Page<Board> searchByKeyword(String keyword, @NonNull Pageable pageable) { // [해결] pageable 파라미터에 @NonNull 추가
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    // [9주차] 새로운 게시글을 데이터베이스에 저장합니다. (글쓰기 기능)
    // [11주차 연습문제] 작성자를 'GUEST'가 아닌 현재 로그인한 사용자의 이메일로 저장하도록 수정되었습니다.
    @SuppressWarnings("null") // [해결] 이 메소드에서 발생하는 null 관련 경고를 무시하도록 설정
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

    // [7주차 연습문제] 기존 게시글의 내용을 수정합니다.
    public void update(@NonNull Long id, AddArticleRequest request) { // [해결] id 파라미터에 @NonNull 추가
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

    // [9주차 연습문제] ID를 이용해 특정 게시글을 삭제합니다.
    public void delete(@NonNull Long id) { // [해결] id 파라미터에 @NonNull 추가
        blogRepository.deleteById(id);
    }
}
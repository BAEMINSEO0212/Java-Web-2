package com.example.demo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
// 페이징 관련 클래스 import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.domain.Board;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;
// 세션 관련 클래스 import
import jakarta.servlet.http.HttpSession;

/**
 * 게시판(Board) 관련 웹 요청을 처리하는 컨트롤러
 */
@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    // favicon.ico 요청을 무시하기 위한 메소드 (브라우저의 자동 요청 처리)
    @GetMapping("/favicon.ico")
    public void favicon() {
    }

    // [9주차] 게시판 목록 조회 (검색 및 페이징 기능 포함)
    // [11주차] 로그인 상태(세션)를 체크하여 비로그인 사용자의 접근을 막는 기능 추가
    @GetMapping("/board_list")
    public String board_list(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            HttpSession session) {

        // [11주차] 세션 체크: 로그인하지 않은 사용자는 로그인 페이지로 리다이렉트
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/member_login";
        }

        // [11주차] 뷰에 로그인한 사용자 이메일을 전달하여 "OOO님 환영합니다" 메시지 출력
        String email = (String) session.getAttribute("email");
        model.addAttribute("email", email);

        // [9주차] 페이징 설정: 한 페이지에 3개의 게시글을 보여주도록 설정
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> boardPage;

        // [9주차] 검색어 유무에 따른 분기 처리
        if (keyword == null || keyword.trim().isEmpty()) {
            boardPage = blogService.findAll(pageable); // 검색어가 없으면 전체 목록 조회
        } else {
            boardPage = blogService.searchByKeyword(keyword, pageable); // 검색어가 있으면 제목으로 검색
        }

        // [9주차 연습문제] 글 번호를 역순으로 표시하기 위한 시작 번호 계산
        long totalElements = boardPage.getTotalElements();
        long startNum = totalElements - (long) page * pageSize;

        // [9주차] 뷰에 페이징 관련 데이터 전달
        model.addAttribute("boards", boardPage);
        model.addAttribute("totalPages", boardPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("startNum", startNum);

        return "board_list";
    }
    // [7주차] 게시글 상세 보기
    // [11주차 연습문제] 현재 로그인한 사용자와 작성자를 비교하기 위해 세션 정보 추가 전달

    @GetMapping("/board_view/{id}")
    public String board_view(Model model, @PathVariable Long id, HttpSession session) {
        Optional<Board> boardData = blogService.findById(id);
        if (boardData.isPresent()) {
            model.addAttribute("board", boardData.get());

            // [11주차 연습문제] 현재 로그인한 사용자 이메일을 뷰로 전달
            String currentUserEmail = (String) session.getAttribute("email");
            model.addAttribute("currentUserEmail", currentUserEmail);

            return "board_view";
        } else {
            return "/error_page/article_error"; // 해당 ID의 게시글이 없으면 에러 페이지로
        }
    }

    // --- 글쓰기 관련 ---

    // [9주차] 글쓰기 페이지(View)를 보여주는 메소드
    @GetMapping("/board_write")
    public String newBoardForm() {
        return "board_write";
    }

    // [9주차] 새로운 게시글을 DB에 저장하는 메소드
    // [11주차 연습문제] 작성자를 'GUEST'가 아닌 현재 로그인한 사용자 이메일로 저장하도록 수정
    @PostMapping("/api/boards")
    public String addBoard(@ModelAttribute AddArticleRequest request, HttpSession session) {
        String email = (String) session.getAttribute("email"); // 세션에서 현재 사용자 이메일 가져오기
        blogService.save(request, email); // 서비스에 이메일 정보와 함께 저장 요청
        return "redirect:/board_list";
    }

    // --- 글 수정 관련 ---

    // [7주차 연습문제] 글 수정 페이지(View)를 보여주는 메소드

    @GetMapping("/board_edit/{id}")
    public String board_edit(Model model, @PathVariable Long id) {
        Optional<Board> boardData = blogService.findById(id);
        if (boardData.isPresent()) {
            model.addAttribute("board", boardData.get());
            return "board_edit";
        } else {
            return "/error_page/article_error";
        }
    }

    // [7주차 연습문제] 수정된 게시글 내용을 DB에 업데이트하는 메소드
    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list";
    }

    // --- 글 삭제 관련 ---

    // [9주차 연습문제] 특정 게시글을 DB에서 삭제하는 메소드
    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list";
    }
}
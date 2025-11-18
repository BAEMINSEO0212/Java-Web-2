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

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    /* --- 기존 Article 관련 코드는 현재 사용하지 않으므로 주석 처리 또는 삭제 --- */

    @GetMapping("/favicon.ico")
    public void favicon() {
        // favicon 요청은 무시
    }

    /**
     * [9주차 핵심] 검색어(keyword)와 페이지 번호(page)를 파라미터로 받아
     * 페이징 처리된 게시글 목록을 보여주는 메소드.
     */
    @GetMapping("/board_list")
    public String board_list(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword) {

        int pageSize = 3; // 한 페이지에 보여줄 게시글 수를 3으로 설정
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> boardPage; // Page 객체로 데이터를 받음

        if (keyword == null || keyword.trim().isEmpty()) {
            boardPage = blogService.findAll(pageable); // 검색어가 없으면 전체 목록 페이징 조회
        } else {
            boardPage = blogService.searchByKeyword(keyword, pageable); // 검색어가 있으면 제목으로 검색
        }

        // [수정] 'list' 변수를 'boardPage'로 올바르게 변경했습니다.
        long totalElements = boardPage.getTotalElements(); // 전체 게시글 수
        long startNum = totalElements - (long) page * pageSize; // 페이지별 시작 번호 (역순)

        model.addAttribute("boards", boardPage); // 뷰에는 Page 객체 자체를 전달
        model.addAttribute("totalPages", boardPage.getTotalPages()); // 전체 페이지 수
        model.addAttribute("currentPage", page); // 현재 페이지 번호
        model.addAttribute("keyword", keyword); // 검색어
        model.addAttribute("startNum", startNum); // [9주차 연습문제] 글 번호 표시를 위한 시작 번호

        return "board_list"; // board_list.html 뷰로 연결
    }

    @GetMapping("/board_view/{id}")
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> boardData = blogService.findById(id);
        if (boardData.isPresent()) {
            // [수정] 상세 페이지에서는 단일 객체를 전달하므로 'board'라는 이름이 더 적합합니다. (기존 'boards' -> 'board')
            model.addAttribute("board", boardData.get());
            return "board_view";
        } else {
            return "/error_page/article_error";
        }
    }

    // --- 글쓰기 관련 ---
    @GetMapping("/board_write")
    public String newBoardForm() {
        return "board_write";
    }

    @PostMapping("/api/boards")
    public String addBoard(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/board_list";
    }

    // --- 글 수정 관련 ---
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

    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list";
    }

    // --- 글 삭제 관련 ---
    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list";
    }
}
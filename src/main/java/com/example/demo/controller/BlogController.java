package com.example.demo.controller;

//import java.util.Optional;
import java.util.List;
//import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board; // [추가] Board 클래스를 import합니다.
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
import org.springframework.ui.Model;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    // --- 기존 Article 게시판 관련 코드는 그대로 둡니다. ---
    @GetMapping("/article_list")
    public String article_list(Model model) {
        // List<Article> list = blogService.findAll(); // 이 부분은 Article 타입을 사용하므로
        // 주석처리합니다.
        // model.addAttribute("articles", list);
        // return "article_list";
        // 참고: Article과 Board를 동시에 사용하려면 BlogService에서 메소드 이름을 분리해야 합니다.
        // 지금은 새로운 board_list에 집중하므로 기존 article_list는 잠시 비워두거나 위처럼 주석처리합니다.
        return "article_list"; // 우선은 페이지만 연결되도록 둡니다.
    }

    @PostMapping("/api/articles")
    public String addArticle(AddArticleRequest request) {
        // blogService.save(request); // BlogService에서 save 메소드를 주석처리했으므로 임시로 주석처리
        return "redirect:/article_list";
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
    }

    @GetMapping("/article_edit/{id}")
    public String article_edit(Model model, @PathVariable Long id) {
        // Optional<Article> list = blogService.findById(id); // Article 관련 로직 임시 주석처리
        // if (list.isPresent()) {
        // model.addAttribute("article", list.get());
        // return "article_edit";
        // } else {
        // return "/error_page/article_error";
        // }
        return "article_edit"; // 페이지만 연결되도록 둡니다.
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        // blogService.update(id, request); // Article 관련 로직 임시 주석처리
        return "redirect:/article_list";
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        // blogService.delete(id); // Article 관련 로직 임시 주석처리
        return "redirect:/article_list";
    }

    // --- 새로운 Board 게시판 관련 코드 ---
    @GetMapping("/board_list")
    public String board_list(Model model) {
        List<Board> list = blogService.findAll();
        model.addAttribute("boards", list);
        return "board_list";
    }

    @GetMapping("/board_view/{id}") // 게시판 상세 보기 링크
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findById(id); // 선택한 게시판 글

        if (list.isPresent()) {
            model.addAttribute("boards", list.get()); // 모델에 Board 객체 추가
            return "board_view"; // board_view.html 연결
        } else {
            return "/error_page/article_error"; // 오류 페이지로 연결
        }
    }

    @GetMapping("/board_edit/{id}")
    public String board_edit(Model model, @PathVariable Long id) {
        // 1. 서비스 계층에 id를 전달하여 해당 게시글의 정보를 가져온다.
        Optional<Board> boardData = blogService.findById(id);

        // 2. 게시글 정보가 존재하면,
        if (boardData.isPresent()) {
            // 3. 모델에 'board'라는 이름으로 게시글 데이터를 담아서,
            model.addAttribute("board", boardData.get());
            // 4. 'board_edit.html' 뷰로 전달한다.
            return "board_edit";
        } else {
            // 게시글이 없으면 에러 페이지로 보낸다.
            return "/error_page/article_error";
        }
    }

    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        // 1. 서비스 계층의 update 메소드를 호출하여 게시글을 수정한다.
        blogService.update(id, request);
        // 2. 수정이 완료되면, 게시판 목록 페이지로 리다이렉트(redirect)한다.
        return "redirect:/board_list";
    }

    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        // 1. 서비스의 delete 메소드를 호출하여 DB에서 해당 id의 데이터를 삭제합니다.
        blogService.delete(id);

        // 2. 삭제가 완료되면, 게시판 목록 페이지로 리다이렉트합니다.
        return "redirect:/board_list";
    }

    @PostMapping("/api/board_add")
    public String addBoard(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/board_list";
    }

}
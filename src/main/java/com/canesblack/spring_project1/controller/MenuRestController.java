package com.canesblack.spring_project1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.service.MenuRestService;

/**
 * REST API 컨트롤러 - 게시판 관련 CRUD 처리
 *
 * 기술 개념:
 * - MVC 패턴에서 이 클래스는 Controller 역할 (프론트와 백 사이의 입구)
 * - RESTful 방식의 API 설계 (@RestController + HTTP 메서드 기반 URL 매핑)
 * - CRUD: Create, Read, Update, Delete 동작을 각각의 HTTP 메서드로 매핑
 * - REST 규칙에 따라 URL은 자원을 명사형으로 표현하고, 행위는 HTTP 메서드로 표현
 */
@RestController
public class MenuRestController {

    @Autowired
    private MenuRestService menuRestService; // Service 계층과 의존성 주입 (DI)

    // READ - 전체 메뉴 조회
    // GET /menu/all
    @GetMapping("/menu/all")
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuRestService.getLists();
        if (menus != null && !menus.isEmpty()) {
            return ResponseEntity.ok(menus); // HTTP 200 OK
        } else {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        }
    }

    // CREATE - 게시판 생성
    // POST /menu/add
    @PostMapping("/menu/add")
    public ResponseEntity<String> addMenu(@RequestBody Menu menu) {
        // 작성일 자동 지정
        if (menu.getIndate() == null || menu.getIndate().isEmpty()) {
            menu.setIndate(LocalDate.now().toString());
        }
        // 조회수는 기본값 0
        menu.setCount(0);
        // 데이터베이스에 삽입
        menuRestService.boardInsert(menu);
        return ResponseEntity.ok("게시글 잘 작성됨");
    }

    // UPDATE - 게시판 수정
    // PUT /menu/update/{idx}
    @PutMapping("/menu/update/{idx}")
    public void updateMenu(@RequestBody Menu menu, @PathVariable("idx") int idx) {
        menu.setIdx(idx); // 경로 변수로 받은 idx를 Menu 객체에 세팅
        menuRestService.boardUpdate(menu); // 서비스 호출
    }

    // DELETE - 게시판 삭제
    // DELETE /menu/delete/{idx}
    @DeleteMapping("/menu/delete/{idx}")
    public void deleteMenu(@PathVariable("idx") int idx) {
        menuRestService.boardDelete(idx); // 서비스 호출
    }

    // READ - 특정 게시글 조회
    // GET /menu/{idx}
    @GetMapping("/menu/{idx}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("idx") int idx) {
        Menu menu = menuRestService.boardContent(idx);
        if (menu != null) {
            return ResponseEntity.ok(menu); // HTTP 200 OK + 데이터
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    // UPDATE - 게시글 조회수 증가
    // PUT /menu/count/{idx}
    @PutMapping("/menu/count/{idx}")
    public void incrementMenuCount(@PathVariable("idx") int idx) {
        menuRestService.boardCount(idx); // 조회수 증가
    }
}

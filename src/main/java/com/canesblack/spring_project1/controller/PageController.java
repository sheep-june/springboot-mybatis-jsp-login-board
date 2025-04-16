package com.canesblack.spring_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.service.MenuRestService;
import com.canesblack.spring_project1.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 전통적인 Spring MVC 기반의 View 렌더링 컨트롤러
 * - JSP 또는 HTML 페이지 반환 (RestController와는 다르게 JSON 반환이 아님)
 * - 사용자 요청을 받아 뷰 이름을 반환하고, Model 객체에 데이터를 담아 View로 전달
 *
 * 기술 개념:
 * - MVC 패턴에서 Controller 계층
 * - CSRF 보호를 위한 토큰 전달 처리 포함
 * - 인증 객체(Authentication)를 통해 로그인 사용자 정보 접근
 */
@Controller
public class PageController {

    @Autowired
    private MenuRestService menuRestService;

    @Autowired
    private UserService userService;

    // 홈 페이지 반환 (index.jsp 또는 index.html)
    // GET / → View 반환 방식 (템플릿 렌더링)
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 회원가입 페이지 요청 시 CSRF 토큰을 View로 전달
    // Spring Security의 CSRF 보호를 위해 hidden input으로 전달해야 함
    @GetMapping("/registerPage")
    public String registerPage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken); // JSP에서 _csrf.token 형태로 사용
        return "register/index";
    }

    // 로그인 페이지 요청 처리 + CSRF 토큰 전달
    @GetMapping("/loginPage")
    public String loginPage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        return "login/index";
    }

    // 게시글 작성 페이지 (관리자 또는 매니저만 접근 가능)
    // 인증된 사용자 정보에서 작성자 이름 조회하여 View에 전달
    @GetMapping("/noticeAddPage")
    public String noticeAddPage(Model model, Authentication authentication) {
        String writer = userService.findWriter(authentication.getName());
        model.addAttribute("writer", writer);
        return "noticeAdd/index";
    }

    // 게시글 상세 확인 페이지
    // 특정 게시글(idx)에 대한 정보를 View에 전달
    @GetMapping("/noticeCheckPage")
    public String showNoticeCheckPage(@RequestParam("idx") int idx, Model model) {
        Menu menu = menuRestService.boardContent(idx);
        model.addAttribute("menu", menu);
        return "noticeCheck/index";
    }

    // 게시글 수정 페이지
    // 수정할 게시글의 데이터를 모델에 담아 View로 전달
    @GetMapping("/noticeModifyPage")
    public String showNoticeModifyPage(@RequestParam("idx") int idx, Model model) {
        Menu menu = menuRestService.boardContent(idx);
        model.addAttribute("menu", menu);
        return "noticeModify/index";
    }
}

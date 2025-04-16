package com.canesblack.spring_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.canesblack.spring_project1.entity.Role;
import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.service.UserService;

/**
 * 사용자 관련 처리 컨트롤러
 * - 회원가입 요청 처리 담당
 * - JSP 기반 View 반환 구조
 *
 * 기술 개념:
 * - MVC 패턴에서 Controller 계층
 * - POST 요청 처리 (@PostMapping)
 * - 스프링 폼 데이터 바인딩 기능 사용 (@ModelAttribute)
 * - 비밀번호 암호화 처리 (BCrypt)
 * - 기본 권한(MEMBER) 설정
 * - 회원가입 후 리다이렉트 방식 적용
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService; // 사용자 관련 비즈니스 로직 처리 계층

    @Autowired
    private PasswordEncoder passwordEncoder; // BCryptPasswordEncoder가 주입됨

    // 회원가입 요청 처리
    // POST /register
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        // 사용자 입력 비밀번호 추출
        String userPassword = user.getPassword();
        System.out.println("userPassword: " + userPassword); // 디버깅용 출력 (실제 운영에서는 제거해야 함)

        // 기본 권한 설정 (회원가입 시 MEMBER로 고정)
        user.setRole(Role.MEMBER);

        // 비밀번호 암호화 처리 (BCrypt 해시 적용)
        String passwordEncoded = passwordEncoder.encode(userPassword);
        user.setPassword(passwordEncoded);

        // 사용자 정보 DB에 저장
        userService.insertUser(user);

        // 로그인 페이지로 리다이렉트 (POST-Redirect-GET 패턴)
        return "redirect:/loginPage";
    }
}

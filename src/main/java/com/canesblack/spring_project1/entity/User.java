package com.canesblack.spring_project1.entity;

/**
 * 사용자(User) 엔티티 클래스
 * - 회원 가입, 로그인, 권한 부여 등 사용자 관련 기능에 사용되는 도메인 객체
 * - DB의 사용자 테이블과 1:1로 매핑되는 구조로 설계됨
 *
 * 기술 개념:
 * - MVC 구조상 Model 계층
 * - 이 클래스는 Spring Security 인증/인가, 회원 가입, 세션 처리 등 여러 기능과 연결됨
 * - 역할(Role)은 Enum을 통해 RBAC 방식으로 구현됨
 * - 비밀번호는 BCrypt로 암호화되어 저장됨 (UserController 참고)
 */
public class User {

    private int idx;             // 사용자 고유 ID (Primary Key)
    private String username;     // 로그인 ID
    private String password;     // 암호화된 비밀번호
    private String writer;       // 작성자 이름 또는 닉네임
    private Role role;           // 권한 (ADMIN, MANAGER, MEMBER)

    public User() {}

    public User(int idx, String username, String password, String writer, Role role) {
        super();
        this.idx = idx;
        this.username = username;
        this.password = password;
        this.writer = writer;
        this.role = role;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
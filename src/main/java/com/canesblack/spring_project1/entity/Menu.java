package com.canesblack.spring_project1.entity;

/**
 * Menu 엔티티 클래스
 * - backend_spring_project 데이터베이스의 menu 테이블과 1:1로 매핑됨
 * - 이 클래스는 MyBatis에서 사용하는 DTO/도메인 객체로, SQL 결과를 자동 바인딩하는 용도로 사용됨
 *
 * 기술 개념:
 * - MVC 구조에서 Model 계층에 해당
 * - DB 테이블 구조와 동일한 필드 정의 → SQL 자동 매핑 (MyBatis 사용 시)
 * - JavaBean 규약: 기본 생성자 + Getter/Setter 구성
 * - REST API 또는 View 렌더링 시 데이터를 주고받는 핵심 데이터 객체
 */
public class Menu {

    // 게시글 고유번호 (기본키, AUTO_INCREMENT)
    private int idx;

    // 작성자 계정 아이디 (회원 ID)
    private String memID;

    // 게시글 제목
    private String title;

    // 게시글 본문 내용
    private String content;

    // 작성자 이름 (혹은 닉네임)
    private String writer;

    // 작성일자 (String으로 관리 중이나, LocalDateTime 등으로 변경 가능)
    private String indate;

    // 조회수 (기본값 0, DB에서 DEFAULT 처리 가능)
    private int count;

    // 기본 생성자 (MyBatis, Jackson 등에서 내부적으로 사용됨)
    public Menu() {
    }

    // 모든 필드를 초기화하는 생성자
    public Menu(int idx, String memID, String title, String content, String writer, String indate, int count) {
        super();
        this.idx = idx;
        this.memID = memID;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.indate = indate;
        this.count = count;
    }

    // Getter와 Setter: 캡슐화를 위한 접근자/설정자 메서드

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getMemID() {
        return memID;
    }

    public void setMemID(String memID) {
        this.memID = memID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

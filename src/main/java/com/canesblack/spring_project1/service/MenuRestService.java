package com.canesblack.spring_project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.mapper.MenuRestMapper;

/**
 * 게시판 관련 비즈니스 로직을 처리하는 Service 클래스
 * - Controller와 Mapper 사이에서 데이터 흐름을 중개하며, 핵심 로직을 처리함
 *
 * 기술 개념:
 * - MVC 구조에서 Service 계층
 * - 단순히 Mapper를 호출하는 구조지만, 실무에서는 여기서 트랜잭션 처리나 데이터 가공 등이 들어갈 수 있음
 * - @Service 어노테이션은 Spring이 이 클래스를 서비스 빈으로 자동 등록하도록 함
 * - @Autowired는 의존성 주입(DI)으로 Mapper를 연결함
 */
@Service
public class MenuRestService {

    @Autowired
    private MenuRestMapper menuRestMapper;

    // READ - 게시글 목록 전체 조회
    public List<Menu> getLists() {
        return menuRestMapper.getLists();
    }

    // CREATE - 게시글 추가
    public void boardInsert(Menu menu) {
        menuRestMapper.boardInsert(menu);
    }

    // READ - 게시글 상세 조회
    public Menu boardContent(int idx) {
        return menuRestMapper.boardContent(idx);
    }

    // DELETE - 게시글 삭제
    public void boardDelete(int idx) {
        menuRestMapper.boardDelete(idx);
    }

    // UPDATE - 게시글 수정
    public void boardUpdate(Menu menu) {
        menuRestMapper.boardUpdate(menu);
    }

    // UPDATE - 게시글 조회수 증가
    public void boardCount(int idx) {
        menuRestMapper.boardCount(idx);
    }
}

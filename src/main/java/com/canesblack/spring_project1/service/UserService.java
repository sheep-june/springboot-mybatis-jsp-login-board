package com.canesblack.spring_project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.mapper.UserMapper;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스
 * - 사용자 등록 및 작성자 이름 조회 기능을 담당
 *
 * 기술 개념:
 * - MVC 구조에서 Service 계층
 * - 데이터 접근을 직접 하지 않고, Mapper(UserMapper)를 통해 DB와 연결
 * - @Service 어노테이션으로 스프링 빈으로 등록됨
 * - @Autowired를 통해 의존성 주입 (DI)
 * - Controller 또는 인증 서비스에서 이 로직을 호출함
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // CREATE - 사용자 등록
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    // READ - 사용자 이름(username)으로 작성자 이름(writer) 조회
    public String findWriter(String username) {
        return userMapper.findWriter(username);
    }
}

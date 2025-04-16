package com.canesblack.spring_project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.canesblack.spring_project1.entity.CustomUser;
import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.mapper.UserMapper;

/**
 * Spring Security 사용자 인증을 위한 서비스 구현체
 * - 로그인 시 사용자 정보를 데이터베이스에서 조회하고 인증 정보를 생성함
 * - UserDetailsService는 스프링 시큐리티에서 인증 처리를 위해 반드시 구현해야 하는 인터페이스
 *
 * 기술 개념:
 * - 이 클래스는 MVC 구조에서 Service 계층이며, Spring Security 인증 로직에 통합됨
 * - loadUserByUsername() 메서드는 스프링 시큐리티가 자동 호출하여 인증 처리에 사용
 * - UserDetails 인터페이스를 구현한 CustomUser 객체를 반환해야 인증이 정상적으로 동작함
 * - DB 조회는 MyBatis를 통해 UserMapper를 사용함
 * - 이 과정에서 사용자가 존재하지 않으면 예외를 던지고, 존재하면 인증용 객체(CustomUser)를 반환함
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // DB에서 사용자 정보 조회
        User user = userMapper.findByUsername(username);
        if (user == null) {
            // 존재하지 않는 사용자일 경우 예외 발생 → Spring Security 인증 실패 처리됨
            throw new UsernameNotFoundException(username + " 존재하지않습니다.");
        }

        // 사용자 정보가 존재하는 경우, Spring Security 인증 객체로 변환하여 반환
        return new CustomUser(user); // 전략 패턴 적용: CustomUser는 UserDetails 구현체
    }
}

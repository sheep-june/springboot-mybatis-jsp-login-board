package com.canesblack.spring_project1.entity;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Spring Security에서 사용하는 사용자 정보 객체 확장
 * - org.springframework.security.core.userdetails.User 클래스를 상속하여 사용자 정의 정보 포함
 *
 * 기술 개념:
 * - Spring Security에서 인증된 사용자 정보를 담는 핵심 클래스는 UserDetails
 * - CustomUser는 UserDetails 구현체인 User를 확장하여 내부에 직접 만든 User 객체를 함께 보관
 * - AuthorityUtils.createAuthorityList(...) 를 사용해 권한 정보를 부여
 * - 이 방식은 SecurityConfig에서 사용자 정보를 로드할 때 주입될 수 있음
 *
 * 사용 위치:
 * - 보통 CustomUserDetailsService 또는 인증 필터에서 반환되는 객체로 활용됨
 * - 세션에 저장된 인증 객체로부터 사용자 정의 정보를 꺼낼 수 있음
 */
public class CustomUser extends org.springframework.security.core.userdetails.User {

    private User user; // 실제 사용자 도메인 객체 보관 (직접 만든 User 클래스)

    public CustomUser(User user) {
        // 부모 클래스인 UserDetails.User 생성자 호출
        // username, password, 권한(Role)을 전달하여 인증 객체로 사용
        super(user.getUsername(), user.getPassword(),
              AuthorityUtils.createAuthorityList(user.getRole().toString()));

        this.user = user; // 추가적으로 도메인 User 정보 보관 가능
    }

    // 필요시 getter를 추가하여 user 정보를 외부에서 사용할 수 있도록 구현 가능
    public User getUser() {
        return user;
    }
}

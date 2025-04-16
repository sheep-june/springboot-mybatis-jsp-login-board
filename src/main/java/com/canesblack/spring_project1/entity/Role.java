package com.canesblack.spring_project1.entity;

/**
 * 사용자 권한(Role)을 정의한 열거형 Enum 클래스
 * - Spring Security의 권한 기반 인가 처리(RBAC)에 사용됨
 *
 * 기술 개념:
 * - Enum은 Java에서 상수 집합을 선언하는 클래스
 * - 각 Role 값은 Security 설정에서 hasAuthority("ADMIN") 등으로 인가 제어에 활용됨
 * - Enum 이름 그대로 DB에 저장하거나, 필요 시 String → Enum 변환 처리 가능
 * - 이 예제에서는 ADMIN, MANAGER, MEMBER 세 가지 권한을 구분함
 *
 * 사용 위치:
 * - User 엔티티의 권한 필드로 포함되어 있음
 * - Security 설정에서 사용자 역할 기반으로 접근 제어에 활용
 */
public enum Role {
    ADMIN,    // 최고 관리자 권한
    MANAGER,  // 게시글 수정/삭제 등의 운영 권한
    MEMBER;   // 일반 사용자 권한
}
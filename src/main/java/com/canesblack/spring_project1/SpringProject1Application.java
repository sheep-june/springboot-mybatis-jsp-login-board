package com.canesblack.spring_project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 애플리케이션의 진입점 (Main 클래스)
 * - 프로젝트 실행 시 가장 먼저 호출되는 클래스이며, 내장 톰캣 서버를 실행함
 *
 * 기술 개념:
 * - @SpringBootApplication: @Configuration, @EnableAutoConfiguration, @ComponentScan이 합쳐진 복합 어노테이션
 *   - 설정 클래스로 인식됨
 *   - 자동 설정 활성화
 *   - 현재 패키지를 기준으로 하위 패키지를 컴포넌트 스캔 대상으로 설정
 * - SpringApplication.run(): 애플리케이션 컨텍스트 생성 및 실행 (서버 시작 포함)
 *
 * 사용 위치:
 * - 전체 프로젝트에서 단 하나만 존재해야 하며, 보통 루트 패키지에 위치함
 * - 이 클래스를 기준으로 모든 @Component, @Service, @Repository, @Controller가 스캔됨
 */
@SpringBootApplication
public class SpringProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringProject1Application.class, args);
    }
}

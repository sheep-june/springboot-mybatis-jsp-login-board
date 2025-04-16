package com.canesblack.spring_project1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Swagger(OpenAPI) 설정 클래스
 * - 개발 중 REST API의 문서를 자동으로 생성하고 확인할 수 있도록 도와주는 도구
 * - 이 설정을 통해 Swagger UI를 통해 엔드포인트를 테스트하거나 설명을 확인할 수 있음
 *
 * 기술 개념:
 * - Swagger는 OpenAPI 명세 기반으로 API 문서 자동 생성 도구
 * - SpringDoc 라이브러리 사용 (springdoc-openapi-ui 등)
 * - REST API 프로젝트에서 거의 표준처럼 사용됨
 * - OpenAPI는 Swagger의 새로운 표준 명칭 (v3 기준)
 *
 * 구성 위치:
 * - 설정 클래스이므로 MVC 패턴 구조상 Config 계층에 해당함
 * - 클라이언트와 개발자에게 API를 명확히 전달하는 수단 역할
 */
@Configuration
public class SwaggerConfig {

    /**
     * OpenAPI 문서 구성 설정
     * - Swagger UI에서 표시될 기본 정보들을 설정함
     * - 이 설정이 있으면 http://localhost:8080/swagger-ui/index.html 경로로 접속 가능 (의존성 포함 시)
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
            .title("Spring_Study001 API Documentation")       // API 문서 제목
            .version("1.0")                                     // 버전 정보
            .description("Spring_Study001 API 명세서")           // 간단한 설명
        );
    }

}

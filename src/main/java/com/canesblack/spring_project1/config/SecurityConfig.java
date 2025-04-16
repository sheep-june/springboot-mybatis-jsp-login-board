package com.canesblack.spring_project1.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Spring Security의 전역 보안 설정 클래스
 * - 인증/인가, 세션 설정, 로그인/로그아웃 흐름, CORS/CSRF 설정 등 포함
 * - 설정 파일은 MVC 구조에서 Config 계층에 해당하며 전역 정책을 다룬다
 */
@Configuration
@EnableWebSecurity // Spring Security 기능 활성화
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF(Cross Site Request Forgery) 방지 설정
            // 쿠키 기반의 CSRF 토큰을 사용하며, JavaScript에서도 접근 가능하도록 설정
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

            // CORS(Cross-Origin Resource Sharing) 설정
            // 프론트엔드와 백엔드가 도메인이 다를 경우 요청 허용을 위한 정책 설정
            .cors(cors -> cors.configurationSource(corsCorsfigurationSource()))

            // 세션 정책 설정
            // IF_REQUIRED: 필요한 경우에만 세션을 생성 (기본 설정)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

            // URL 별 접근 권한 설정
            .authorizeHttpRequests(authz -> authz
                // DispatcherType에 따른 요청 허용
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR, DispatcherType.REQUEST).permitAll()

                // 누구나 접근 가능한 경로 설정 (비인증 요청 허용)
                .requestMatchers("/", "/WEB-INF/views/**").permitAll()
                .requestMatchers("/", "/loginPage", "/logout", "/noticeCheckPage", "/registerPage", "/menu/all").permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .requestMatchers("/resources/**", "/WEB-INF/**").permitAll()

                // 관리자 또는 매니저만 접근 가능한 경로 (권한 기반 인가 - RBAC)
                .requestMatchers("/noticeAddPage", "/noticeModifyPage").hasAnyAuthority("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.POST, "/menu/add").hasAnyAuthority("ADMIN", "MANAGER")   // REST - Create
                .requestMatchers(HttpMethod.POST, "/menu/update").hasAnyAuthority("ADMIN", "MANAGER") // REST - Update
                .requestMatchers(HttpMethod.DELETE, "/menu/delete").hasAnyAuthority("ADMIN", "MANAGER") // REST - Delete

                // 위에서 명시되지 않은 요청은 인증된 사용자만 허용
                .anyRequest().authenticated()
            )

            // 로그인 설정 (세션 기반 인증 방식, 전통적인 웹앱 구조)
            .formLogin(login -> login
                .loginPage("/loginPage")                 // 사용자 정의 로그인 페이지
                .loginProcessingUrl("/login")           // 로그인 처리 URL (POST)
                .failureUrl("/loginPage?error=true")     // 로그인 실패 시 이동 경로
                .usernameParameter("username")          // 로그인 폼에서 사용되는 ID 필드명
                .passwordParameter("password")          // 로그인 폼에서 사용되는 PW 필드명
                .successHandler(authenticationSuccessHandler()) // 로그인 성공 후 핸들러 주입 (전략 패턴 기반 처리)
                .permitAll()
            )

            // 로그아웃 설정
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 요청 경로
                .logoutSuccessUrl("/")                                     // 로그아웃 성공 시 이동 경로
                .invalidateHttpSession(true)                                // 세션 무효화
                .deleteCookies("JSESSIONID")                                // 쿠키 제거
                .permitAll()
            );

        return http.build(); // 설정된 보안 정책을 필터 체인으로 반환
    }

    /**
     * 로그인 성공 시 실행되는 커스텀 핸들러
     * - 인증된 사용자 정보를 세션에 저장
     * - 관리자 권한 여부를 세션에 플래그로 저장
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();

                // 관리자 또는 매니저 권한 체크 (RBAC 기반)
                boolean isManager = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority ->
                                grantedAuthority.getAuthority().equals("ADMIN") ||
                                grantedAuthority.getAuthority().equals("MANAGER"));

                if (isManager) {
                    session.setAttribute("MANAGER", true);
                }

                // 인증된 사용자 ID 세션에 저장
                session.setAttribute("username", authentication.getName());
                session.setAttribute("isAuthenticated", true);

                // 메인 페이지로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    /**
     * 비밀번호 암호화 방식 설정
     * BCrypt는 단방향 해시 알고리즘이며 salt가 자동으로 적용되어 보안성이 높음
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * CORS 정책 설정
     * - 특정 도메인에서만 백엔드 요청을 허용함 (보안 목적)
     * - 프론트엔드와 포트/도메인이 다른 경우 필요
     */
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsCorsfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 출처 (프론트엔드 서버 도메인)
        configuration.setAllowedOrigins(Arrays.asList(
            "http://3.34.196.150:8080", 
            "https://3.34.196.150:8080"
        ));

        // 허용할 HTTP 메서드 설정
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // 허용할 헤더 설정
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
            new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
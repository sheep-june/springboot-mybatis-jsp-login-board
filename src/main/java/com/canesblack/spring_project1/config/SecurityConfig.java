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







@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		//스피링 시큐리티 기능을 사용하고자 할대 이 메소드 안에 작성한다 암기
		
		//scrf해킹 보호조치 
		http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		
		//cors는 특정 서버로만 데이터를 넘길 수 있도록 설정할 수 있음
		.cors(cors -> cors.configurationSource(corsCorsfigurationSource()))
		
		
		//세션 설정
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
		
		.authorizeHttpRequests(authz->authz.requestMatchers("/","/loginPage","/logout","/noticeCheckPage","/register","/menu/all")
			.permitAll()
			.requestMatchers(HttpMethod.POST,"/login").permitAll()
			.requestMatchers("/resources/**","/WEB-INF/**").permitAll()
			.requestMatchers("/noticerAdd","noticeModifyPage").hasAnyAuthority("ADMIN","MANAGER")
			.requestMatchers(HttpMethod.POST, "/menu/add").hasAnyAuthority("ADMIN", "MANAGER")
		    .requestMatchers(HttpMethod.POST, "/menu/update").hasAnyAuthority("ADMIN", "MANAGER")
		    .requestMatchers(HttpMethod.DELETE, "/menu/delete").hasAnyAuthority("ADMIN", "MANAGER")
		    .anyRequest().authenticated()
		)
		
		
		
		.formLogin(
			login->login.loginPage("/loginPage")//url작성해서 로그인 페이지로 이동할때 
			.loginProcessingUrl("/login")
			.failureUrl("/loginPage?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(authenticationSuccessHandlr())
			.permitAll()
		)
		.logout(logout->logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// /logout URL을 통해서 로그아웃이 진행됨
				.logoutSuccessUrl("/")//로그아웃성공후 이 url로 리다이렉팅
				.invalidateHttpSession(true)//세션무효화=> 세션공간안에 있던 데이터가 사라짐
				.deleteCookies("JSESSIONID")//쿠키삭제
				.permitAll()//위 기능을 수행시켤려면 이 메서드 실행
		);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandlr() {
		return new SimpleUrlAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				//로그인 성공 했을때 특별한 기능 넣고싶을때 ex 권한 세션 
				HttpSession session = request.getSession();//세션 기능을 가지고 
				boolean isManager = authentication.getAuthorities().stream().anyMatch(grantedAuthoirity -> 
					grantedAuthoirity.getAuthority().equals("ADMIN")||
					grantedAuthoirity.getAuthority().equals("MANAGER"));
				if(isManager) {
					session.setAttribute("MANAGER", true);
				}
				session.setAttribute("username", authentication.getName());
				session.setAttribute("isAuthenticatied", true);
				//request.getContextPath()=>localhost:8080
				response.sendRedirect(request.getContextPath()+"/");
				
				
				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}
	
	@Bean
	public org.springframework.web.cors.CorsConfigurationSource corsCorsfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://3.34.196.150:8080", "https://3.34.196.150:8080"));
		//localhost:8080서버에서는 프론트에서 백엔드단 혹은 백엔드단에서 프론트단인데 데이터를 주고받을수 있게 만든것
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
		
	}
	
	
	
	
	
	
	
	
	
}

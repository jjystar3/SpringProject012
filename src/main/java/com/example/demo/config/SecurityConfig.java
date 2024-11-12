package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.ApiCheckFilter;
import com.example.demo.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	UserDetailsService customUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ApiCheckFilter apliCheckFilter() {
		return new ApiCheckFilter();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// CSRF 비활성화
		http.csrf().disable();
		// 세션 사용안함
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// 인증 없이 접근 가능한 경로 설정 (로그인 등)
		// 처음에는 모든 경로를 권한이 없는 사용자에게 허용
		http.authorizeHttpRequests()
		.requestMatchers("/login", "/register","/board/*","/member/*").permitAll();

		// formLogin 비활성화
		http.formLogin().disable();
		
		return http.build();
	}
	
}

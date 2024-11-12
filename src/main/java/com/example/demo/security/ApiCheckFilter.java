package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.security.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiCheckFilter extends OncePerRequestFilter {

	// 토큰 검사가 필요한 URL 패턴 목록
	String[] patternArr = { "/board/*", "/member/*" };

	// 패턴 검사기
	AntPathMatcher antPathMatcher;

	// JWT 유틸 (토큰 생성 및 검사)
	JWTUtil jwtUtil;
	
	// 사용자 인증 서비스
	UserDetailsService userDetailsService;

	public ApiCheckFilter(UserDetailsService userDetailsService) {
		this.antPathMatcher = new AntPathMatcher();
		this.jwtUtil = new JWTUtil();
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		for (String pattern : patternArr) {
			boolean result = antPathMatcher.match(pattern, request.getRequestURI());
			if (result == true) {
				System.out.println("ApliCheckFilter...............");
				System.out.println("ApliCheckFilter...............");
				System.out.println("ApliCheckFilter...............");
			}
		}

	}
}

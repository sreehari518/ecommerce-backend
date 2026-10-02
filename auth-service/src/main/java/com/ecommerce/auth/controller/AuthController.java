package com.ecommerce.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.LoginResponse;
import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.dto.RegisterResponse;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/auth/register")
	public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
		
		return authService.register(request);
		
	}
	
	@PostMapping("/auth/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		
		return authService.login(request);
	}
	
//	@GetMapping("/admin/test")
//	public String adminTest() {
//		return "Admin access granted";
//	}

}

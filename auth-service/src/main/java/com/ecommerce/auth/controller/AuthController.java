package com.ecommerce.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.service.AuthService;

@RestController
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/auth/register")
	public User register(@RequestBody RegisterRequest request) {
		
		return authService.register(request);
		
	}
	
	@PostMapping("/auth/login")
	public User login(@RequestBody LoginRequest request) {
		
		return authService.login(request);
	}

}

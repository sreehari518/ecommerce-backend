package com.ecommerce.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.LoginResponse;
import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.security.JwtService;

@Service
public class AuthService {

		private final UserRepository userRepository;
		
		private final PasswordEncoder passwordEncoder;
		
		private final JwtService jwtService;
		
		public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService) {
			this.userRepository = userRepository;
			this.passwordEncoder = passwordEncoder;
			this.jwtService = jwtService;
		}
		
		public User register(RegisterRequest request) {
			
			if(userRepository.existsByEmail(request.getEmail())) {
				throw new RuntimeException("Email already registered");
			}
			
			
			User user = new User();
			
			user.setName(request.getName());
			user.setEmail(request.getEmail());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			
			user.setRole("USER");
			
			return userRepository.save(user);
			
		}
		
		public LoginResponse login(LoginRequest request) {
			
			User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("Invalid email or password"));
			
			if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
				
				throw new RuntimeException("Invalid Email or Password");
			}
			
			LoginResponse response = new LoginResponse();
			
			response.setName(user.getName());
			response.setEmail(user.getEmail());
			response.setToken(jwtService.generateToken(user.getEmail(),user.getRole()));
			
			return response;
		}
}

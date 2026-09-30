package com.ecommerce.auth.service;

import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;

@Service
public class AuthService {

		private final UserRepository userRepository;
		public AuthService(UserRepository userRepository) {
			this.userRepository = userRepository;
		}
		
		public User register(RegisterRequest request) {
			
			User user = new User();
			
			user.setName(request.getName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			
			user.setRole("USER");
			
			return userRepository.save(user);
			
		}
}

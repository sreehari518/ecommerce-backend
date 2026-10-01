package com.ecommerce.auth.security;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Service
public class JwtService {
	
	private final SecretKey secretKey = Keys.hmacShaKeyFor("my-super-secret-key-for-ecommerce-auth-service-2026".getBytes());
	
	public String generateToken(String email,String role) {
		return Jwts.builder().subject(email).claim("role", role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60)).signWith(secretKey).compact();
	}
	public String extractEmail(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
	}
	
	public String extractRole(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role",String.class);
	}
	
}

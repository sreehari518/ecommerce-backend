package com.ecommerce.auth.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtService jwtService;
	
	public JwtAuthenticationFilter(JwtService jwtService) {
		
		this.jwtService = jwtService;
	}
	
	@Override
	protected void doFilterInternal(
			
		jakarta.servlet.http.HttpServletRequest request,
		
		jakarta.servlet.http.HttpServletResponse response,
		
		jakarta.servlet.FilterChain filterChain) throws
	
		jakarta.servlet.ServletException,
		java.io.IOException{
		
		if(request.getServletPath().equals("/auth/register") || request.getServletPath().equals("/auth/login") ) {
			filterChain.doFilter(request, response);
			return;
		}
			 
			String authHeader = request.getHeader("Authorization");
			
			if(authHeader == null || ! authHeader.startsWith("Bearer")) {
				filterChain.doFilter(request, response);
				
				return;
			}
			
			String token = authHeader.substring(7);
			String email = jwtService.extractEmail(token);
			String role = jwtService.extractRole(token);
			System.out.println("JWT ROLE = " + role);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null,java.util.List.of(new SimpleGrantedAuthority("ROLE_"+role)));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			System.out.println("JWT Token: "+ token);
			filterChain.doFilter(request,response);
	}
	
}

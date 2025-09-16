package com.omkar.config;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtProvider {
	private Key key = Keys.hmacShaKeyFor(jwtConstant.SECRATE_KEY.getBytes());
	
	public String genrateTkone(Authentication auth) {
		String jwt = Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+846000000))
				.claim("email", auth.getName())
				.signWith(key).compact();
		
		return jwt;
	}
	
	public String getEmailFromToken(String jwt) {
		try {
	        String token = jwt.trim();
	        if (token.startsWith("Bearer ")) {
	            token = token.substring(7).trim(); // Remove 'Bearer ' and trim
	        }
		
		Claims claims =Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		
		String email= String.valueOf(claims.get("email"));
		
		  return claims.get("email", String.class);
	    } catch (Exception e) 
		{
	        throw new RuntimeException("Invalid JWT token: " + e.getMessage());
}
	}
}

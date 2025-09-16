package com.omkar.config;

import java.io.IOException;
import java.security.Key;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader(jwtConstant.JWT_HEADER);
		
		if(jwt!=null){
			jwt=jwt.substring(7);
			try {
				Key key = Keys.hmacShaKeyFor(jwtConstant.SECRATE_KEY.getBytes());
				
				Claims claims =Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				
				String email= String.valueOf(claims.get("email"));
				
//				@SuppressWarnings("unchecked")
//				List<String> roles = (List<String>) claims.get("authorities");
//				String authorities = String.join(",", roles);
				
//				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,null);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				
				 
			} catch (Exception e) {
				throw new BadCredentialsException("invalid token...from jwt validator");
			}
		}
		filterChain.doFilter(request, response);
		
	}
	

}

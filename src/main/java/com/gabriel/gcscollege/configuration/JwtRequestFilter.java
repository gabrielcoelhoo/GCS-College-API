package com.gabriel.gcscollege.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	 	
		@Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private JwtService jwtService;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

	        final String requestTokenHeader = request.getHeader("Authorization");

	        String email = null;
	        String jwtToken = null;

	        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	            jwtToken = requestTokenHeader.substring(7);
	            try {
	                email = jwtUtil.getEmailFromToken(jwtToken);
	            } catch (IllegalArgumentException e) {
	                System.out.println("Unable to get JWT Token");
	            } catch (ExpiredJwtException e) {
	                System.out.println("JWT Token has expired");
	            }
	        } else {
	            System.out.println("JWT token does not start with Bearer");
	        }

	        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            EmailDetails emailDetails = jwtService.loadUserByEmail(email);

	            if (jwtUtil.validateToken(jwtToken, emailDetails)) {

	                EmailPasswordAuthenticationToken emailPasswordAuthenticationToken = new EmailPasswordAuthenticationToken(emailDetails, null, emailDetails.getAuthorities());
	                emailPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(emailPasswordAuthenticationToken);
	            }
	        }
	        filterChain.doFilter(request, response);

	    }

	}

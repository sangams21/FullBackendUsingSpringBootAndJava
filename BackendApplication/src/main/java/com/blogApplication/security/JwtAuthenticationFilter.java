//package com.blogApplication.security;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter{
//
//	@Autowired
//	private UserDetailsService userDeatilService;
//	
//	@Autowired
//	private JwtTokenHelper jwtTokenHelper;
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		
//		String requestToken=request.getHeader("authorization");
//		
//		System.out.println(requestToken);
//		
//		String username="null";
//		String token="null";
//		
//		if(requestToken!=null && requestToken.startsWith("Bearer")){
//			
//			token=requestToken.substring(7);
//			try {
//				username=this.jwtTokenHelper.getUsernameFromToken(token);
//			}catch(IllegalArgumentException e) {
//				System.out.println("unable to get jwt token register");
//				
//			}catch(ExpiredJwtException e) {
//				System.out.println("jwt token has Expired");
//			}catch (MalformedJwtException e) {
//
//				System.out.println("invalid Jwt");
//			}
//			
//			
//		}else {
//			System.out.println("jwt is not start with bearer");
//		}
//		
//		
//		//vaidation start
//		
//		if(username!=null  && SecurityContextHolder.getContext().getAuthentication()==null) {
//			
//			UserDetails userDetails = this.userDeatilService.loadUserByUsername(username);
//			if(this.jwtTokenHelper.validateToken(token, null)) {
//				
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
//						new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
//				
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//				
//			}else {
//				System.out.println("Invalid Jwt toakn");
//			}
//
//		
//		}else {
//			System.out.println("username is null");
//		}
//		
//		filterChain.doFilter(request, response);
//		
//	}
//
//}

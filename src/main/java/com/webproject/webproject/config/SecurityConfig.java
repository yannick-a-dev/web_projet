//package com.webproject.webproject.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import com.webproject.webproject.security.JwtAuthenticationEntryPoint;
//import com.webproject.webproject.security.JwtAuthenticationFilter;
//import com.webproject.webproject.service.UserDetailsServiceImpl;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//	private UserDetailsServiceImpl userDetailsService;
//	
//	private JwtAuthenticationEntryPoint handler;
//
//	public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
//		this.userDetailsService = userDetailsService;
//		this.handler = handler;
//	}
//	
//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter();
//	}
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() {
//		return super.authenticationManagerBean();
//	}
//}

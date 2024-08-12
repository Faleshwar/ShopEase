package com.shopease.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shopease.security.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(c->c.disable())
				.authorizeHttpRequests(a->a
						.requestMatchers("/register/**", "/login/**", "/product/**").permitAll()
						.requestMatchers("/seller/**").hasAuthority("SELLER")
						.requestMatchers("/users", "/admin/**").hasAuthority("ADMIN")
						.anyRequest()
						.authenticated())
				.userDetailsService(userDetailsService)
				.sessionManagement(c->c
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}

}

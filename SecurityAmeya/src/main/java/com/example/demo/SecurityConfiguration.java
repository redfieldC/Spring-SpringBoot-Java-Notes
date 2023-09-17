package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.csrf(csrf -> csrf.disable())
//		.authorizeHttpRequests(auth -> {
//			auth.anyRequest().permitAll();
//		});
//		return http.build();
//	}
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.csrf(csrf -> csrf.disable())
//		.authorizeHttpRequests(auth -> {
//			try {
//				auth.antMatchers(HttpMethod.DELETE, "/employees/**").authenticated()
//				.anyRequest().permitAll()
//				.and().httpBasic();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		});
//		return http.build();
//	}
	
	
	//authentication and authorization
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> {
			try {
				auth.antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT,"/employees/**").hasRole("USER")
				.antMatchers().authenticated()
				.anyRequest().permitAll()
				.and().httpBasic();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		});
		return http.build();
	}
	
	//In memory user details defined with username, password ad role
	@Bean
	public UserDetailsService detailsService() {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user1 = User.withUsername("user123")
				.password(encoder.encode("user123"))
				.roles("USER")
				.build();
		UserDetails user2 = User.withUsername("admin")
				.password(encoder.encode("admin123"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

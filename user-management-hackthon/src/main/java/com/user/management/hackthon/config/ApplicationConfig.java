package com.user.management.hackthon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.user.management.hackthon.repository.UserRepository;

@Configuration
public class ApplicationConfig {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not Found!!"));
		
		/*
		 * new UserDetailsService() {
		 * 
		 * @Override public UserDetails loadUserByUsername(String username) throws
		 * UsernameNotFoundException { // TODO Auto-generated method stub return null; }
		 * };
		 */
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
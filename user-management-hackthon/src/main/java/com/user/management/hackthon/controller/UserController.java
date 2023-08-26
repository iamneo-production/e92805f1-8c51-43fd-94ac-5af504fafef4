package com.user.management.hackthon.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.hackthon.request.UserLoginRequest;
import com.user.management.hackthon.request.UserRegisterRequest;
import com.user.management.hackthon.response.UserRegisterResponse;
import com.user.management.hackthon.service.UserAuthenticationService;

@RestController
@RequestMapping("/api/v1/management")
public class UserController {

	@Autowired
	private UserAuthenticationService userAuthenticationService;
	
	@RequestMapping("/display")
	public String display() {
		return "User management is UP "+new Date();
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
		System.out.println("calling register: "+request.toString());
		return ResponseEntity.ok(userAuthenticationService.register(request));
//		return ResponseEntity.ok(new AuthenticationResponse());
	}
	
	@PostMapping("/login")
	public void userLogin(@RequestBody UserLoginRequest request) {
		
	}
	
}

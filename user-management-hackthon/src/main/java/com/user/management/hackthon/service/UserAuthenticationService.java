package com.user.management.hackthon.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.hackthon.config.JwtService;
import com.user.management.hackthon.contants.Role;
import com.user.management.hackthon.entity.User;
import com.user.management.hackthon.repository.UserRepository;
import com.user.management.hackthon.request.UserRegisterRequest;
import com.user.management.hackthon.response.UserRegisterResponse;

@Service
public class UserAuthenticationService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public UserRegisterResponse register(UserRegisterRequest request) {

		var user = new User();
		user.setId(generateId());
		user.setUserName(request.getUserName());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		System.out.println("passwordEncoder: "+passwordEncoder);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPasswrodConfirm(passwordEncoder.encode(request.getConfirmPassword()));
		user.setRegisterDate(toDate(new Date()));
		user.setContact(request.getContact());
		
		if("Doctor".equalsIgnoreCase(request.getRoleType())) {
			user.setRole(Role.DOCTOR);
		} else {
			user.setRole(Role.PATIENT);
		}
		
		if("male".equalsIgnoreCase(request.getGender())) {
			user.setGender('M');
		} else {
			user.setGender('F');
		}
		
		userRepository.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		UserRegisterResponse response = new UserRegisterResponse();
		response.setToken(jwtToken);
		response.setRole(user.getRole().name());
		response.setUserName(user.getUserName());
		return response;
	}

	private String generateId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	private Date toDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rDate = null;
		try {
			rDate = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rDate;
	}
	
}

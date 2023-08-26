package com.user.management.hackthon.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.hackthon.config.JwtService;
import com.user.management.hackthon.contants.Role;
import com.user.management.hackthon.entity.Doctor;
import com.user.management.hackthon.entity.Patient;
import com.user.management.hackthon.entity.User;
import com.user.management.hackthon.repository.DoctorRepository;
import com.user.management.hackthon.repository.PatientRepository;
import com.user.management.hackthon.repository.UserRepository;
import com.user.management.hackthon.request.UserLoginRequest;
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

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	public UserRegisterResponse register(UserRegisterRequest request) {
		UserRegisterResponse response = new UserRegisterResponse();
		var user = new User();
		user.setId(generateId());
		user.setUserName(request.getUserName());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPasswrodConfirm(passwordEncoder.encode(request.getConfirmPassword()));
		user.setRegisterDate(toDate(new Date()));
		user.setContact(request.getContact());

		if ("Doctor".equalsIgnoreCase(request.getRoleType())) {
			user.setRole(Role.DOCTOR);
		} else {
			user.setRole(Role.PATIENT);
		}

		User savedUser = userRepository.save(user);
		saveActor(request, user, savedUser);
//		var jwtToken = jwtService.generateToken(user);

//		response.setToken(jwtToken);
		response.setRole(savedUser.getRole().name());
		response.setUserName(savedUser.getUserName());
		return response;
	}

	private void saveActor(UserRegisterRequest request, User user, User savedUser) {
		if("Doctor".equalsIgnoreCase(savedUser.getRole().name())) {
			Doctor doctor = new Doctor();
			doctor.setId(user.getId());
			doctor.setDoctorName(user.getUsername());
			doctor.setSpecialization(request.getSpecialization());
			doctor.setGender("male".equalsIgnoreCase(request.getGender()) ? "M" : "F");
			Doctor savedActor = doctorRepository.save(doctor);
			System.out.println(savedActor.getId() + " = "+savedActor.getDoctorName());
		} else {
			Patient patient = new Patient();
			patient.setId(user.getId());
			patient.setPatientName(user.getUsername());
			patient.setGender("male".equalsIgnoreCase(request.getGender()) ? "M" : "F");
			Patient savedActor = patientRepository.save(patient);
			System.out.println(savedActor.getId() + " = "+savedActor.getPatientName());
		}
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

	public Map<String, String> userLogin(UserLoginRequest request) {
		Map<String, String> response = new HashMap<>();
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		var user = userRepository.findByUserName(request.getUserName()).orElseThrow();

		var jwtToken = jwtService.generateToken(user);
		response.put("token", jwtToken);
		var roleType = user.getRole().name();
		response.put("role", roleType);
		if("Doctor".equalsIgnoreCase(roleType)) {
			response.put("doctorId", user.getId());
		} else {
			response.put("patientId", user.getId());
		}
		return response;
	}

}

package com.user.management.hackthon.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.hackthon.config.JwtService;
import com.user.management.hackthon.contants.Role;
import com.user.management.hackthon.contants.UserContants;
import com.user.management.hackthon.entity.Doctor;
import com.user.management.hackthon.entity.Patient;
import com.user.management.hackthon.entity.User;
import com.user.management.hackthon.exception.UserNotFoundException;
import com.user.management.hackthon.repository.DoctorRepository;
import com.user.management.hackthon.repository.PatientRepository;
import com.user.management.hackthon.repository.UserRepository;
import com.user.management.hackthon.request.UserLoginRequest;
import com.user.management.hackthon.request.UserRegisterRequest;
import com.user.management.hackthon.response.UserRegisterResponse;

@Service
public class UserAuthenticationService {
	
	private static Logger LOG = LoggerFactory.getLogger(UserAuthenticationService.class);

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

	public UserRegisterResponse register(UserRegisterRequest request) throws UserNotFoundException {
		UserRegisterResponse response = new UserRegisterResponse();

		var user = mapDtoToEntity(request);

		validateUserAlreadyRegister(user);

		User savedUser = userRepository.save(user);
		saveActor(request, user, savedUser);

		response.setRole(savedUser.getRole().name());
		response.setUserName(savedUser.getUserName());

		return response;
	}

	public Map<String, String> userLogin(UserLoginRequest request) throws UsernameNotFoundException {
		Map<String, String> response = new HashMap<>();
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		var user = userRepository.findByUserName(request.getUserName()).orElseThrow();

		var jwtToken = jwtService.generateToken(user);
		response.put(UserContants.TOKEN, jwtToken);
		var roleType = user.getRole().name();
		response.put(UserContants.ROLE, roleType);
		if (UserContants.DOCTOR.equalsIgnoreCase(roleType)) {
			response.put(UserContants.DOCTOR_ID, user.getId());
		} else {
			response.put(UserContants.PATIENT_ID, user.getId());
		}
		return response;
	}

	private void validateUserAlreadyRegister(User user) {
		User usr = userRepository.findByEmail(user.getEmail()).orElse(new User());
		userAlreadyRegister(user.getUsername(), usr.getUsername());
		if (user.getEmail().equalsIgnoreCase(usr.getEmail())) {
			throw new UserNotFoundException(UserContants.EMAIL_ID_EXISTS);
		}

		usr = userRepository.findByContact(user.getContact()).orElse(new User());
		userAlreadyRegister(user.getUsername(), usr.getUsername());
		if (user.getContact().equalsIgnoreCase(usr.getContact())) {
			throw new UserNotFoundException(UserContants.PHONE_NUMBER_EXISTS);
		}

	}

	private void userAlreadyRegister(String input, String data) {
		if (input.equalsIgnoreCase(data)) {
			throw new UserNotFoundException(UserContants.USER_ID_EXISTS);
		}
	}

	private void saveActor(UserRegisterRequest request, User user, User savedUser) {
		if (UserContants.DOCTOR.equalsIgnoreCase(savedUser.getRole().name())) {
			Doctor doctor = new Doctor();
			doctor.setId(user.getId());
			doctor.setDoctorName(user.getUsername());
			doctor.setSpecialization(request.getSpecialization());
			doctor.setGender(UserContants.MALE.equalsIgnoreCase(request.getGender()) ? UserContants._M : UserContants._F);
			Doctor savedActor = doctorRepository.save(doctor);
			LOG.debug("Doctor object is saved {0}, {1}", savedActor.getId(), savedActor.getDoctorName());
		} else {
			Patient patient = new Patient();
			patient.setId(user.getId());
			patient.setPatientName(user.getUsername());
			patient.setGender(UserContants.MALE.equalsIgnoreCase(request.getGender()) ? UserContants._M : UserContants._F);
			Patient savedActor = patientRepository.save(patient);
			LOG.debug("Patient object is saved {0}, {1}", savedActor.getId(), savedActor.getPatientName());
		}
	}

	private User mapDtoToEntity(UserRegisterRequest request) {
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

		if (UserContants.DOCTOR.equalsIgnoreCase(request.getRoleType())) {
			user.setRole(Role.DOCTOR);
		} else {
			user.setRole(Role.PATIENT);
		}
		return user;
	}

	private String generateId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll(UserContants.HYPHEN, UserContants.EMPTY);
	}

	private Date toDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(UserContants.DATE_FORMAT);
		Date rDate = null;
		try {
			rDate = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rDate;
	}

}

package com.user.management.hackthon.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterRequest {

	@JsonProperty("User Id")
	private String userName;
	
	@JsonProperty("First Name")
	private String firstName;
	
	@JsonProperty("Last Name")
	private String lastName;
	
	@JsonProperty("Email Id")
	private String email;
	
	@JsonProperty("Phone")
	private String contact;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("Confirm Password")
	private String confirmPassword;
	
	@JsonProperty("Role")
	private String roleType;
	
	@JsonProperty("Specialist")
	private String specialization;
	
	@JsonProperty("Gender")
	private String gender;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
}

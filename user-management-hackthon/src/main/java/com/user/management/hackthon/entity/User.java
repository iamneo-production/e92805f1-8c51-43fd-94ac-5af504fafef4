package com.user.management.hackthon.entity;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.user.management.hackthon.contants.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="USER_TBL")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Column(name="USER_NAME", nullable = false, unique = true, length = 12)
	private String userName;
	
	@Column(name="FIRST_NAME", nullable = false, length = 25)
	private String firstName;
	
	@Column(name="LAST_NAME", length = 25)
	private String lastName;
	
	@Column(name="EMAIL", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name="CONTACT", length=10, nullable = false, unique = true)
	private String contact;
	
	@Column(name="PASSWORD", length = 100, nullable = false)
	private String password;
	
	@Column(name="PASSWORD_CONFIRM", length = 100, nullable = false)
	private String passwrodConfirm;
	
	/*
	 * @Column(name="GENDER", length = 1, nullable = false) private char gender;
	 */
	
	@Column(name="REG_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public String getPasswrodConfirm() {
		return passwrodConfirm;
	}

	public void setPasswrodConfirm(String passwrodConfirm) {
		this.passwrodConfirm = passwrodConfirm;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	/*
	 * public char getGender() { return gender; }
	 * 
	 * public void setGender(char gender) { this.gender = gender; }
	 */

	
}

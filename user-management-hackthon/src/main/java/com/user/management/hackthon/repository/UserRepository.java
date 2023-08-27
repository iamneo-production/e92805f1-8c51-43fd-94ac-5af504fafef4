package com.user.management.hackthon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.hackthon.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByUserName(String userName);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByContact(String contact);
	
}

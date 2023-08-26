package com.user.management.hackthon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.hackthon.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

}

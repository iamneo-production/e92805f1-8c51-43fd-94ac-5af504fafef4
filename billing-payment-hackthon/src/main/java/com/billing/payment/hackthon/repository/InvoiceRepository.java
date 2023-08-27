package com.billing.payment.hackthon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.payment.hackthon.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>{

	public Optional<Invoice> findByPatientId(String patientId);

	public List<Invoice> findByPatientIdIn(List<String> patientIds); 

}

package com.billing.payment.hackthon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.payment.hackthon.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

}

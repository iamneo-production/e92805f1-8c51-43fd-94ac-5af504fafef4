package com.billing.payment.hackthon.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="PAYMENT_TBL")
public class Payment {

	@Id
	@Column(name="PYMT_ID")
	private String id;
	
	@Column(name="PATIENT_ID")
	private String patientId;
	
	@Column(name="PATIENT_NAME")
	private String patientName;
	
	@Column(name="PYMT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date pymtDate;
	
	@Column(name="ITEM")
	private String item;
	
	@Column(name="PYMT_AMOUNT")
	private double amount;
	
	@Column(name="PYMT_STATUS")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getPymtDate() {
		return pymtDate;
	}

	public void setPymtDate(Date pymtDate) {
		this.pymtDate = pymtDate;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

}

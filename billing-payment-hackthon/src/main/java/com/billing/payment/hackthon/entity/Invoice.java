package com.billing.payment.hackthon.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="INVOICE_TBL")
public class Invoice {

	@Id
	@Column(name="INVOICE_ID")
	private String id;
	
	@Column(name="PATIENT_ID")
	private String patientId;
	
	@Column(name="PATIENT_NAME")
	private String patientName;
	
	@Column(name="INVOICE_ITEM")
	private String item;
	
	@Column(name="INVOIE_AMOUNT")
	private double amount;
	
	@Column(name="INVOICE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;

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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

}

package com.billing.payment.hackthon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDTO {

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("Payment Id")
	private String pymtId;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("Payment Date")
	private String pymtDate;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("Item")
	private String item;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("Payment Amount")
	private String pymtAmount;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("Payment Status")
	private String status;

	public String getPymtId() {
		return pymtId;
	}

	public void setPymtId(String pymtId) {
		this.pymtId = pymtId;
	}

	public String getPymtDate() {
		return pymtDate;
	}

	public void setPymtDate(String pymtDate) {
		this.pymtDate = pymtDate;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPymtAmount() {
		return pymtAmount;
	}

	public void setPymtAmount(String pymtAmount) {
		this.pymtAmount = pymtAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

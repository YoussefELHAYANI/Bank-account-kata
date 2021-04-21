package com.sg.kata.accountmanagement.operations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperationDTO {
	
	private String description;
	private BigDecimal amount;
	private LocalDateTime date ;
	private String ownerId;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}

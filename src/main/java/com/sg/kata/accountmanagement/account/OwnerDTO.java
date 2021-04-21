package com.sg.kata.accountmanagement.account;

import java.math.BigDecimal;

public class OwnerDTO {
	private String name;
	private BigDecimal sold;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSold() {
		return sold;
	}
	public void setSold(BigDecimal sold) {
		this.sold = sold;
	}
}

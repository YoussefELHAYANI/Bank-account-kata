package com.sg.kata.accountmanagement.account;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sg.kata.accountmanagement.operations.Operation;

/**
 * Entity owner have one or many operation(s)
 * 
 * 
 * @author Youssef EL HAYANI
 *
 */

@Entity
public class Owner {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private BigDecimal sold;
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Operation> operation;
	
	public Set<Operation> getOperation() {
		return operation;
	}
	public void setOperation(Set<Operation> operation) {
		this.operation = operation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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

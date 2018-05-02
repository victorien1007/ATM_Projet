package com.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account", catalog = "atm")

public class Account implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private Double balance;
	private String type;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String id) {
		this.id = id;
	}

	/** full constructor */
	public Account(String id, String userId, Double balance, String type) {
		this.id = id;
		this.userId = userId;
		this.balance = balance;
		this.type = type;
	}

	// Property accessors
	@Id

	@Column(name = "id", unique = true, nullable = false, length = 50)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "user_id", length = 50)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "balance", precision = 50, scale = 0)

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "type", length = 50)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
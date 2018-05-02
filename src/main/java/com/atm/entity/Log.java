package com.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "log", catalog = "atm")

public class Log implements java.io.Serializable {

	// Fields

	private Integer id;
	private String srcId;
	private String desId;
	private String type;
	private String money;
	private String date;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(String srcId, String desId, String type, String money, String date) {
		this.srcId = srcId;
		this.desId = desId;
		this.type = type;
		this.money = money;
		this.date = date;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "srcId", length = 50)

	public String getSrcId() {
		return this.srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	@Column(name = "desId", length = 50)

	public String getDesId() {
		return this.desId;
	}

	public void setDesId(String desId) {
		this.desId = desId;
	}

	@Column(name = "type", length = 50)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "money", length = 50)

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "date", length = 50)

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
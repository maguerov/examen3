package com.cenfotec.graphql.entities;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "full_Name", nullable = false)
	private String fullName;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "billing_Address", nullable = false)
	private String billingAddress;	
	
	@Column(name = "card_Number", nullable = false)
	private String cardNumber;
	
	@Column(name = "exp_Date", nullable = false)
	private LocalDate expDate;

	
	private transient String formattedDate;
	
	public String getFormattedDate() {
	return getExpDate().toString();
	}
}

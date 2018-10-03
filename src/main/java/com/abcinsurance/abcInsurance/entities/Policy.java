package com.abcinsurance.abcInsurance.entities;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * @author Pieterv
 *
 */
@Entity
@Data
@Table(name = "policy", schema = "public")
public class Policy {

	@Id
	private String id;
	@Column(unique = true)
	private String customerId;
	private String debtOrderDate;
	private String policyInceptionDate;

	@Column(columnDefinition = "integer auto_increment", unique = true)
	private String policyNumber;
	private boolean policyStatus;
	private BigDecimal premium;
	private String transactionDate;

	@PrePersist
	public void prepersist() {
		if (id == null || id.isEmpty()) {
			id = UUID.randomUUID().toString();
		}
	}

}

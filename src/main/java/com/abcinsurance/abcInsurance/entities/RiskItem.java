package com.abcinsurance.abcInsurance.entities;

import java.math.BigDecimal;
import java.util.UUID;

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
@Table(name = "riskitem", schema = "public")
public class RiskItem {

	@Id
	private String id;
	private String policyId;
	private BigDecimal premium;
	private RiskItems riskItemName;
	private String riskStartDate;

	@PrePersist
	public void prepersist() {
		if (id == null || id.isEmpty()) {
			id = UUID.randomUUID().toString();
		}
	}
	
}

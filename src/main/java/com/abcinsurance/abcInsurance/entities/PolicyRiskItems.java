package com.abcinsurance.abcInsurance.entities;

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
@Table(name = "policyriskitems", schema = "public")
public class PolicyRiskItems {

	//Don't think that Ill be using this, but yet in-case I need a policy/risk-item mapping
	@Id
	private String id;

	private String policyId;
	private String riskItemId;

	@PrePersist
	public void prepersist() {
		if (id == null || id.isEmpty()) {
			id = UUID.randomUUID().toString();
		}
	}

}

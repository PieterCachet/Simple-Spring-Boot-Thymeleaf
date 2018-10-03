package com.abcinsurance.abcInsurance.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.abcinsurance.abcInsurance.dto.CreateCustomerRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Pieterv
 *
 */
@Entity
@Data
@Table(name = "customer", schema = "public")
@Slf4j
public class Customer {

	@Id
	private String id;
	private String firstName;
	private String surname;
	private String dateOfBirth;
	@Size(min = 13, max = 13)
	@Column(unique = true)
	private String zaId;

	@PrePersist
	public void prepersist() throws ParseException {
		if (id == null || id.isEmpty()) {
			id = UUID.randomUUID().toString();
		}

		if (dateOfBirth == null || dateOfBirth.isEmpty()) {
			try {
				Date dob = new SimpleDateFormat("yyMMdd").parse(zaId.substring(0, 6));
				DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
				dateOfBirth = dateFormat.format(dob);
			} catch (Exception e) {
				log.warn("Unknown date from id {}", zaId);
				dateOfBirth = "Unknown date";
			}
		}

	}

	public Customer() {

	}

	public Customer(CreateCustomerRequest ccr) {
		this.firstName = ccr.getFirstName();
		this.surname = ccr.getSurname();
		this.zaId = ccr.getZaId();
	}

}

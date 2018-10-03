package com.abcinsurance.abcInsurance.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import lombok.Data;

/***
 * Create Custom Request DTO
 * @author Pieterv
 *
 */
@Data
public class CreateCustomerRequest {

	@Size(min = 1, max = 255)
	private String firstName;
	@Size(min = 1, max = 255)
	private String surname;
	@Digits(integer = 13, fraction = 0)
	@Size(min = 13, max = 13)
	private String zaId;

}

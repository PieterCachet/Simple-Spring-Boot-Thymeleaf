package com.abcinsurance.abcInsurance.config.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abcinsurance.abcInsurance.dto.CreateCustomerRequest;
import com.abcinsurance.abcInsurance.entities.Customer;
import com.abcinsurance.abcInsurance.services.CustomerService;
import com.abcinsurance.abcInsurance.services.FakeCustomerCreationService;

import lombok.extern.slf4j.Slf4j;

/**
 * RestFull Customer Controller
 * 
 * @author Pieterv
 *
 */
@RestController
@Slf4j
public class CustomerRestfullController {

	private CustomerService custService;
	private FakeCustomerCreationService fccService;

	@RequestMapping(path = "/createNewCustomer", method = RequestMethod.POST)
	public ResponseEntity<String> creatNewCustomer(@RequestBody @Valid CreateCustomerRequest request) {
		try {
			Customer customer = new Customer(request);
			custService.addCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			log.warn("Exception Reach whilst adding new customer", e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}

	}

	@RequestMapping(path = "/getAllCustomers", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomer() {
		return new ResponseEntity<List<Customer>>(custService.getAllCustomers(), HttpStatus.OK);
	}

	@RequestMapping(path = "/creatFakeCustomers", method = RequestMethod.GET)
	public ResponseEntity<String> creatFakeCustomers() {
		fccService.creatFakeCustomerData();
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Autowired
	public void setFccService(FakeCustomerCreationService fccService) {
		this.fccService = fccService;
	}

	@Autowired
	public void setCustService(CustomerService custService) {
		this.custService = custService;
	}

}

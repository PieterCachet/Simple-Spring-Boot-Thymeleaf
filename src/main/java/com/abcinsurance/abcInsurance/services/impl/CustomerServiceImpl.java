package com.abcinsurance.abcInsurance.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcinsurance.abcInsurance.entities.Customer;
import com.abcinsurance.abcInsurance.entities.Policy;
import com.abcinsurance.abcInsurance.repos.CustomerRepository;
import com.abcinsurance.abcInsurance.repos.PolicyRepository;
import com.abcinsurance.abcInsurance.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Pieterv
 *
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private PolicyRepository policyRepo;

	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public void addCustomer(Customer customer) {
		custRepo.save(customer);

	}

	@Override
	public Policy findPolicy(Customer customer) {
		return policyRepo.findByCustomerId(customer.getId());

	}

	@Override
	public List<Customer> getAllUnpairedCustomers() {
		// Return a List of customer that haven`t been paired with policy yet.
		List<Customer> upairdList = new ArrayList<>();
		List<Customer> customers = custRepo.findAll();
		List<Policy> policies = policyRepo.findAll();
		customers.forEach((Customer customer) -> {
			boolean isWithin = false;
			for (Policy policy : policies) {
				if (policy.getCustomerId().equals(customer.getId())) {
					isWithin = true;
				}
			}
			if (!isWithin) {
				upairdList.add(customer);
			}
		});
		return upairdList;
	}

	@Override
	public Customer findCustomer(Customer customer) {
		return custRepo.findById(customer.getId()).get();
	}

	@Override
	public void updateCustomer(Customer customer) {
		try {
			Date dob = new SimpleDateFormat("yyMMdd").parse(customer.getZaId().substring(0, 6));
			DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
			customer.setDateOfBirth(dateFormat.format(dob));
		} catch (Exception e) {
			log.warn("Unknown date from id {}", customer.getZaId());
			customer.setDateOfBirth("Unknown date");
		}
		addCustomer(customer);
	}
}

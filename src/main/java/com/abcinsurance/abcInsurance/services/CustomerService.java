package com.abcinsurance.abcInsurance.services;

import java.util.List;

import com.abcinsurance.abcInsurance.entities.Customer;
import com.abcinsurance.abcInsurance.entities.Policy;

/**
 * Customer Services
 * @author Pieterv
 *
 */
public interface CustomerService {

	/**
	 * Get All Customers
	 * 
	 * @return List<Customer>
	 */
	public List<Customer> getAllCustomers();

	/**
	 * Find Specific Customer
	 * 
	 * @param customer
	 * @return Customer
	 */
	public Customer findCustomer(Customer customer);

	/**
	 * Add new Customer
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer);

	/**
	 * Find Policy By Customer, This should be in Policy Serive Interface!!
	 * 
	 * @param customer
	 * @return Policy
	 */
	public Policy findPolicy(Customer customer);

	/**
	 * Get All Customers that haven`t been paired to Policy.
	 * 
	 * @return List<Customer>
	 */
	public List<Customer> getAllUnpairedCustomers();

	/**
	 * 
	 * @param customer
	 */
	public void updateCustomer(Customer customer);

}

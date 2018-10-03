package com.abcinsurance.abcInsurance.services;

import java.util.List;

import com.abcinsurance.abcInsurance.entities.Policy;

/**
 * Policy Services
 * @author Pieterv
 *
 */
public interface PolicyService {
	
	/**
	 * Get All Policies
	 * @return List<Policy>
	 */
	public List<Policy> getAllPolicies();
	/**
	 * Create new Policy
	 * @param policy
	 */
	public void addPolicy(Policy policy);

}

package com.abcinsurance.abcInsurance.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcinsurance.abcInsurance.entities.Policy;
import com.abcinsurance.abcInsurance.repos.PolicyRepository;
import com.abcinsurance.abcInsurance.services.PolicyService;

/**
 * 
 * @author Pieterv
 *
 */
@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepo;

	@Override
	public List<Policy> getAllPolicies() {
		return policyRepo.findAll();
	}

	@Override
	public void addPolicy(Policy policy) {
		policyRepo.save(policy);
	}

}

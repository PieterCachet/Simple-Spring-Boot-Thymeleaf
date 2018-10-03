package com.abcinsurance.abcInsurance.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcinsurance.abcInsurance.entities.Policy;
import com.abcinsurance.abcInsurance.entities.RiskItem;
import com.abcinsurance.abcInsurance.repos.PolicyRepository;
import com.abcinsurance.abcInsurance.repos.RiskItemRepository;
import com.abcinsurance.abcInsurance.services.RiskItemService;

/**
 * 
 * @author Pieterv
 *
 */
@Service
public class RiskItemServiceImpl implements RiskItemService {

	@Autowired
	private RiskItemRepository riskRepo;

	@Autowired
	private PolicyRepository policyRepo;

	@Override
	public List<RiskItem> getAllRiskItems() {
		return riskRepo.findAll();
	}

	@Override
	public void addRiskItem(RiskItem riskitem) {
		// Make sure the Policy Premium include new value;
		Policy policy = policyRepo.findById(riskitem.getPolicyId()).get();
		policy.setPremium(
				riskitem.getPremium().add(policy.getPremium() != null ? policy.getPremium() : new BigDecimal("0")));
		policyRepo.save(policy);

		riskRepo.save(riskitem);
	}

	@Override
	public List<RiskItem> getAllRiskItemsByPolicyId(Policy policy) {
		return riskRepo.findByPolicyId(policy.getId());

	}

}

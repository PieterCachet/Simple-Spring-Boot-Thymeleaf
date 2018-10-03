package com.abcinsurance.abcInsurance.services.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcinsurance.abcInsurance.entities.Customer;
import com.abcinsurance.abcInsurance.entities.Policy;
import com.abcinsurance.abcInsurance.entities.RiskItem;
import com.abcinsurance.abcInsurance.entities.RiskItems;
import com.abcinsurance.abcInsurance.repos.CustomerRepository;
import com.abcinsurance.abcInsurance.repos.PolicyRepository;
import com.abcinsurance.abcInsurance.repos.RiskItemRepository;
import com.abcinsurance.abcInsurance.services.FakeCustomerCreationService;

@Service
public class FakeCustomerCreationServiceImpl implements FakeCustomerCreationService {

	private CustomerRepository custRepo;
	private PolicyRepository polRepo;
	private RiskItemRepository riskRepo;

	@Override
	public void creatFakeCustomerData() {
		Customer customer = new Customer();
		customer.setFirstName("Pieter");
		customer.setSurname("Cachet");
		customer.setZaId("8905088888888");
		String custID = custRepo.save(customer).getId();

		Policy policy = new Policy();
		policy.setCustomerId(custID);
		policy.setDebtOrderDate("2018/08/01");
		policy.setPolicyInceptionDate("2018/08/01");
		policy.setPolicyStatus(Boolean.TRUE);
		policy.setTransactionDate("2018/08/01");
		String polId = polRepo.save(policy).getId();

		RiskItem riskItem = new RiskItem();
		riskItem.setPolicyId(polId);
		riskItem.setPremium(new BigDecimal("5000"));
		riskItem.setRiskItemName(RiskItems.PrivateVehicle);
		riskItem.setRiskStartDate("2018/08/01");
		riskRepo.save(riskItem);

		policy = polRepo.findById(polId).get();
		policy.setPremium(
				riskItem.getPremium().add(policy.getPremium() != null ? policy.getPremium() : new BigDecimal("0")));
		polRepo.save(policy);

		riskItem = new RiskItem();
		riskItem.setPolicyId(polId);
		riskItem.setPremium(new BigDecimal("7000"));
		riskItem.setRiskItemName(RiskItems.CommercialVehicle);
		riskItem.setRiskStartDate("2018/08/01");
		riskRepo.save(riskItem);

		policy.setPremium(policy.getPremium().add(riskItem.getPremium()));
		polRepo.save(policy);

	}

	@Autowired
	public void setCustRepo(CustomerRepository custRepo) {
		this.custRepo = custRepo;
	}

	@Autowired
	public void setPolRepo(PolicyRepository polRepo) {
		this.polRepo = polRepo;
	}

	@Autowired
	public void setRiskRepo(RiskItemRepository riskRepo) {
		this.riskRepo = riskRepo;
	}

}

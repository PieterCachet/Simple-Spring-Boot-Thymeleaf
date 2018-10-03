package com.abcinsurance.abcInsurance.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abcinsurance.abcInsurance.dto.UIAlerts;
import com.abcinsurance.abcInsurance.entities.Policy;
import com.abcinsurance.abcInsurance.services.CustomerService;
import com.abcinsurance.abcInsurance.services.PolicyService;
import com.abcinsurance.abcInsurance.services.impl.AlertBuilder;

import lombok.extern.slf4j.Slf4j;
/**
 * Policy Controller
 * @author Pieterv
 *
 */
@Controller
@Slf4j
public class PolicyController {

	private PolicyService policyService;
	private CustomerService customerService;

	@Autowired
	private AlertBuilder ab;

	@RequestMapping(path = "/policies", method = RequestMethod.GET)
	public Callable<String> getPolicy(Model model) {
		return () -> {
			model.addAttribute("tab", "policies");
			try {
				model.addAttribute("policies", policyService.getAllPolicies());
			} catch (Exception e) {
				log.warn("Exception reached whilst getting policy list", e);
				ab.alertBuilder(model, UIAlerts.WARNING, e.getMessage());
				return "policy/policies";
			}
			return "policy/policies";
		};
	}

	@RequestMapping(path = "/addpolicy", method = RequestMethod.GET)
	public Callable<String> addPolicy(Model model) {
		return () -> {
			model.addAttribute("tab", "policies");
			try {
				model.addAttribute("policy", new Policy());
				model.addAttribute("customers", customerService.getAllUnpairedCustomers());
			} catch (Exception e) {
				log.warn("Exception reached whilst getting policy list", e);
				ab.alertBuilder(model, UIAlerts.WARNING, e.getMessage());
				return "policy/addpolicy";
			}
			return "policy/addpolicy";
		};
	}

	@RequestMapping(path = "/addpolicy", method = RequestMethod.POST)
	public Callable<String> addPolicy(Model model, @ModelAttribute final Policy policy) {
		return () -> {
			model.addAttribute("tab", "policies");
			try {
				policyService.addPolicy(policy);
				model.addAttribute("policies", policyService.getAllPolicies());
				ab.alertBuilder(model, UIAlerts.SUCCESS, "Policy Successfully created");
			} catch (DataIntegrityViolationException e) {
				log.warn("Exception reached whilst adding new Policy", e);
				ab.alertBuilder(model, UIAlerts.WARNING, "Policy Already Link to Customer");
			} catch (Exception ex) {
				log.warn("Exception reached whilst adding new Policy", ex);
				ab.alertBuilder(model, UIAlerts.WARNING, ex.getMessage());
				return "policy/policies";
			}
			return "policy/policies";
		};
	}

	@Autowired
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}

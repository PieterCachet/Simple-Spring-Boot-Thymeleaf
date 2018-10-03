package com.abcinsurance.abcInsurance.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abcinsurance.abcInsurance.dto.UIAlerts;
import com.abcinsurance.abcInsurance.entities.Customer;
import com.abcinsurance.abcInsurance.entities.Policy;
import com.abcinsurance.abcInsurance.entities.RiskItem;
import com.abcinsurance.abcInsurance.services.CustomerService;
import com.abcinsurance.abcInsurance.services.RiskItemService;
import com.abcinsurance.abcInsurance.services.impl.AlertBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Customer Controller
 * @author Pieterv
 *
 */
@Controller
@Slf4j
public class CustomerController {

	private CustomerService customerService;
	private RiskItemService riskService;

	@Autowired
	private AlertBuilder ab;

	@RequestMapping(path = "/customers", method = RequestMethod.GET)
	public Callable<String> getCustomers(Model model) {
		return () -> {
			model.addAttribute("tab", "customers");
			try {
				model.addAttribute("customers", customerService.getAllCustomers());
			} catch (Exception e) {
				log.warn("Exception reached whilst getting customer list", e);
				ab.alertBuilder(model, UIAlerts.WARNING, e.getMessage());
				return "customer/customers";
			}
			return "customer/customers";
		};
	}

	@RequestMapping(path = "/addcustomer", method = RequestMethod.GET)
	public Callable<String> addCustomer(Model model) {
		return () -> {
			model.addAttribute("tab", "customers");
			try {
				model.addAttribute("customer", new Customer());

			} catch (Exception e) {
				log.warn("Exception reached whilst getting customer list", e);
				ab.alertBuilder(model, UIAlerts.WARNING, e.getMessage());
				return "customer/addcustomer";
			}
			return "customer/addcustomer";
		};
	}

	@RequestMapping(path = "/editcustomer", method = RequestMethod.POST)
	public Callable<String> editCustomer(Model model, @Valid final Customer customer) {
		return () -> {
			model.addAttribute("tab", "customers");
			try {
				model.addAttribute("customer", customerService.findCustomer(customer));
			} catch (Exception e) {
				log.warn("Exception reached whilst fetching customer details", e);
				model.addAttribute("customer", new Customer());
				ab.alertBuilder(model, UIAlerts.DANGER, "Expection occured whilst fetching customer details.");
				return "customer/editcustomer";
			}
			return "customer/editcustomer";
		};
	}

	@RequestMapping(path = "/updatecustomer", method = RequestMethod.POST)
	public Callable<String> updateCustomer(Model model, @ModelAttribute final Customer customer) {
		return () -> {
			model.addAttribute("tab", "customers");
			try {
				customerService.updateCustomer(customer);
				model.addAttribute("customers", customerService.getAllCustomers());

			} catch (Exception e) {
				log.warn("Exception reached whilst updating customer details", e);
				model.addAttribute("customer", new Customer());
				ab.alertBuilder(model, UIAlerts.DANGER, "Expection occured whilst updating customer details.");
				return "customer/customers";
			}
			return "customer/customers";
		};
	}

	@RequestMapping(path = "/addcustomer", method = RequestMethod.POST)
	public Callable<String> addCustomer(Model model, @ModelAttribute final Customer customer) {
		return () -> {
			model.addAttribute("tab", "customers");
			try {
				customerService.addCustomer(customer);
				ab.alertBuilder(model, UIAlerts.SUCCESS, "Customer Successfully created");
				model.addAttribute("customers", customerService.getAllCustomers());
			} catch (Exception ex) {
				log.warn("Exception reached whilst adding new customer", ex);
				ab.alertBuilder(model, UIAlerts.WARNING, ex.getMessage());
				return "customer/customers";
			}
			return "customer/customers";
		};
	}

	@RequestMapping(path = "/customerpolicy", method = RequestMethod.POST)
	public Callable<String> customerpolicy(Model model, @Valid final Customer customer) {
		return () -> {
			model.addAttribute("tab", "customers");
			try {
				Customer cust = customerService.findCustomer(customer);
				Policy policy = customerService.findPolicy(customer);
				List<RiskItem> riskitmes = riskService.getAllRiskItemsByPolicyId(policy);
				policy.setPremium(new BigDecimal("0"));
				for (RiskItem riskItem : riskitmes) {
					policy.setPremium(policy.getPremium().add(riskItem.getPremium()));
				}

				model.addAttribute("customer", cust);
				model.addAttribute("policy", policy);
				model.addAttribute("riskitems", riskitmes);

			} catch (Exception e) {
				log.warn("Exception reached whilst fetching customer details", e);
				ab.alertBuilder(model, UIAlerts.WARNING, e.getMessage());
				return "customer/customers";
			}
			return "customer/customerdetails";

		};
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setRiskService(RiskItemService riskService) {
		this.riskService = riskService;
	}
}

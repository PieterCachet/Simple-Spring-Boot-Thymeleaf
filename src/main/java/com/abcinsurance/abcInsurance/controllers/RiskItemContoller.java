package com.abcinsurance.abcInsurance.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abcinsurance.abcInsurance.dto.UIAlerts;
import com.abcinsurance.abcInsurance.entities.RiskItem;
import com.abcinsurance.abcInsurance.services.PolicyService;
import com.abcinsurance.abcInsurance.services.RiskItemService;
import com.abcinsurance.abcInsurance.services.impl.AlertBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Risk Items Controller
 * @author Pieterv
 *
 */
@Controller
@Slf4j
public class RiskItemContoller {

	private RiskItemService riskItemService;
	private PolicyService policyService;

	@Autowired
	private AlertBuilder ab;

	@RequestMapping(path = "/addriskitem", method = RequestMethod.GET)
	public Callable<String> addRiskitem(Model model) {
		return () -> {
			model.addAttribute("tab", "riskitems");
			try {
				model.addAttribute("policies", policyService.getAllPolicies());
				model.addAttribute("riskitem", new RiskItem());
			} catch (Exception e) {
				log.warn("Exception reached whilst getting risk item list", e);
				ab.alertBuilder(model, UIAlerts.WARNING, e.getMessage());
				return "riskitem/addriskitem";
			}
			return "riskitem/addriskitem";
		};
	}

	@RequestMapping(path = "/addriskitem", method = RequestMethod.POST)
	public Callable<String> addRiskitem(Model model, @ModelAttribute final RiskItem riskitem) {
		return () -> {
			model.addAttribute("tab", "riskitems");
			try {
				riskItemService.addRiskItem(riskitem);
				model.addAttribute("riskitems", riskItemService.getAllRiskItems());
				ab.alertBuilder(model, UIAlerts.SUCCESS, "Risk Item Successfully created");
			} catch (Exception ex) {
				log.warn("Exception reached whilst adding risk item", ex);
				ab.alertBuilder(model, UIAlerts.WARNING, ex.getMessage());
				return "riskitem/riskitems";
			}
			return "riskitem/riskitems";
		};
	}

	@RequestMapping(path = "/riskitems", method = RequestMethod.GET)
	public Callable<String> getRiskItems(Model model) {
		return () -> {
			model.addAttribute("tab", "riskitems");
			try {
				model.addAttribute("riskitems", riskItemService.getAllRiskItems());
			} catch (Exception e) {
				log.warn("Exception reached whilst getting risk item list", e);
				ab.alertBuilder(model, UIAlerts.WARNING, e.getMessage());
				return "riskitem/riskitems";
			}
			return "riskitem/riskitems";
		};
	}

	@Autowired
	public void setRiskItemService(RiskItemService riskItemService) {
		this.riskItemService = riskItemService;
	}

	@Autowired
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

}

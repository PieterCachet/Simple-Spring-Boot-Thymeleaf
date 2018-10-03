package com.abcinsurance.abcInsurance.controllers;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abcinsurance.abcInsurance.dto.GraphData;
import com.abcinsurance.abcInsurance.services.DashboardService;

/**
 * General Controller
 * @author Pieterv
 *
 */
@Controller
public class GeneralController {

	@Autowired
	private DashboardService dashboardService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String landing() {
		return "landing";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(path = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model) {
		model.addAttribute("tab", "dashboard");
		return "dashboard";
	}

	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		model.addAttribute("tab", "admin");
		return "admin";
	}

	@RequestMapping(value = "/getGraphData", method = RequestMethod.GET)
	@ResponseBody
	public Callable<List<GraphData>> graphData() {
		return () -> dashboardService.getGraphData();
	}

}

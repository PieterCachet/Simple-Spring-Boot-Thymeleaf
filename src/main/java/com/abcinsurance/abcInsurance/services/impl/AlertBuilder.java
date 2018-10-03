package com.abcinsurance.abcInsurance.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.abcinsurance.abcInsurance.dto.UIAlerts;

/**
 * 
 * @author Pieterv
 *
 */
@Service
public class AlertBuilder {

	/**
	 * Create Bootstrap Alert.
	 * 
	 * @param model
	 *            Model
	 * @param uiAlert
	 *            UIAlerts.
	 * @param message
	 *            Message displayed to user.
	 */
	public void alertBuilder(Model model, UIAlerts uiAlert, String message) {
		model.addAttribute("alert", uiAlert);
		model.addAttribute("alertMessage", message);

	}

}

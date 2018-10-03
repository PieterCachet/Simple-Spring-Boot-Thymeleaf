package com.abcinsurance.abcInsurance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.abcinsurance.abcInsurance.dto.UIAlerts;
import com.abcinsurance.abcInsurance.services.impl.AlertBuilder;

public class AlertBuilderTests {

	@Test
	public void alertBuilder() {
		AlertBuilder ab = new AlertBuilder();

		String msg = "Unit Test Message";
		ModelMap modelEx = new ExtendedModelMap();

		ab.alertBuilder((Model) modelEx, UIAlerts.DANGER, msg);

		assertNotNull(modelEx);

		assertTrue(modelEx.containsAttribute("alert"));
		assertTrue(modelEx.containsAttribute("alertMessage"));

		assertTrue(modelEx.containsValue(UIAlerts.DANGER));
		assertTrue(modelEx.containsValue(msg));

	}
}

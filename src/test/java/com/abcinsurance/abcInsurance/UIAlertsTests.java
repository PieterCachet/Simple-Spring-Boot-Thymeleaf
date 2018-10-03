package com.abcinsurance.abcInsurance;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abcinsurance.abcInsurance.dto.UIAlerts;

public class UIAlertsTests {

	@Test
	public void test() {
		assertTrue(UIAlerts.values().length >= 1);
	}
}

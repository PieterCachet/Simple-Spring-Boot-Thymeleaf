package com.abcinsurance.abcInsurance.services;

import java.util.List;

import com.abcinsurance.abcInsurance.entities.Policy;
import com.abcinsurance.abcInsurance.entities.RiskItem;

/**
 * Risk item Services
 * 
 * @author Pieterv
 *
 */
public interface RiskItemService {
	/**
	 * Get All Risk Items
	 * 
	 * @return List<RiskItem>
	 */
	public List<RiskItem> getAllRiskItems();

	/**
	 * Add Risk Item
	 * 
	 * @param riskitem
	 */
	public void addRiskItem(RiskItem riskitem);

	/**
	 * Get All Risk Items by Policy Id.
	 * 
	 * @param policy
	 * @return List<RiskItem>
	 */
	public List<RiskItem> getAllRiskItemsByPolicyId(Policy policy);

}

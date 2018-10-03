package com.abcinsurance.abcInsurance.services;

import java.util.List;

import com.abcinsurance.abcInsurance.dto.GraphData;

/**
 * Dashboard Services
 * 
 * @author Pieterv
 *
 */
public interface DashboardService {

	/**
	 * Get Grath Data
	 * 
	 * @return List<GraphData>
	 */
	List<GraphData> getGraphData();

}

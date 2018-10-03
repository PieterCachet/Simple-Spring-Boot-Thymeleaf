package com.abcinsurance.abcInsurance.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abcinsurance.abcInsurance.dto.GraphData;
import com.abcinsurance.abcInsurance.services.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Override
	public List<GraphData> getGraphData() {
		List<GraphData> graphDataList = new ArrayList<>();
		GraphData graphData = new GraphData();

		graphData.setKey("New Users");
		graphData.setValue(getRandomLong());
		graphDataList.add(graphData);

		graphData = new GraphData();
		graphData.setKey("New Policies");
		graphData.setValue(getRandomLong());
		graphDataList.add(graphData);

		graphData = new GraphData();
		graphData.setKey("New Risk Items");
		graphData.setValue(getRandomLong());
		graphDataList.add(graphData);

		return graphDataList;
	}

	private long getRandomLong() {
		long leftLimit = 1L;
		long rightLimit = 100000L;
		return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	}

}

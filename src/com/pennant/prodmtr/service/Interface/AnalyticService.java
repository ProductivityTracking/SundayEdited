package com.pennant.prodmtr.service.Interface;

import java.util.List;

import com.pennant.prodmtr.model.Dto.AnalyticsDto;
import com.pennant.prodmtr.model.view.TotalAnalysis;

public interface AnalyticService {

	public AnalyticsDto getUserProjDetails(int user_id);

	public List<String> getCompletedTasksByDate();

	public TotalAnalysis getTotalAnalysis();

}
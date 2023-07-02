package com.pennant.prodmtr.controller;

import java.sql.SQLException;
import java.util.List;
import com.pennant.prodmtr.model.view.TotalAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pennant.prodmtr.model.Dto.AnalyticsDto;
import com.pennant.prodmtr.service.Interface.AnalyticService;

@Controller
public class AnalyticsController {
	@Autowired
	AnalyticService analyticService;
	@RequestMapping(value = "/getDashboardAnalytics", method = RequestMethod.POST)
	public @ResponseBody String getDashboardAnalytics(Model model) throws SQLException {
		System.out.println("inside getDashboardAnalytics");

	    	    List<String> completedTasksByDate = analyticService.getCompletedTasksByDate();
	    	    Gson gson = new Gson();
	    	    String json = gson.toJson(completedTasksByDate);

	    	    return json;
	}

	@RequestMapping(value = "/getDashboardAllAnalytics", method = RequestMethod.POST)
	public @ResponseBody String getDashboardAllAnalytics(Model model) throws SQLException {
		System.out.println("inside getDashboardAllAnalytics");

			TotalAnalysis totalAnalysis = analyticService.getTotalAnalysis() ;
//	    	    System.out.println("completedTasksByDate "+completedTasksByDate);
	    	    Gson gson = new Gson();
	    	    String json = gson.toJson(totalAnalysis);
System.out.println("json "+json );
	    	    return json;
	}
	
	@RequestMapping(value = "/getUserAnalytics", method = RequestMethod.POST)
	public @ResponseBody String getProjAnalysisById(@RequestParam("user_id") Integer user_id) throws SQLException {
		// List<ProjectSummary> projectWiseSummary = analyticService.getUserProjDetails(user_id);
		System.out.println("called user analy with id "+user_id);
		AnalyticsDto analytics = analyticService.getUserProjDetails(user_id);
		Gson gson = new Gson();
		String json = gson.toJson(analytics);
		return json;
	}

}

package com.koley.kaustav.covid19.CovidTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koley.kaustav.covid19.CovidTracker.model.StateStatistics;
import com.koley.kaustav.covid19.CovidTracker.service.ConfirmedCases;

@Controller
public class HomeController {
	
	@Autowired
	private ConfirmedCases confirmedCases;
	
	@GetMapping
	public String getHomeResource(Model model) {
		List<StateStatistics> totalList =  confirmedCases.getCurrentList();
		long globalTotalCases =  totalList.stream().mapToInt(StateStatistics::getTotalCasesByToday).sum();
		long globalNewCases =  totalList.stream().mapToInt(StateStatistics::getDiffFromPrevDay).sum();
		model.addAttribute("confirmedCases", totalList);
		model.addAttribute("globalTotalCases", globalTotalCases);
		model.addAttribute("globalNewCases", globalNewCases);
		return "home";
	}

}

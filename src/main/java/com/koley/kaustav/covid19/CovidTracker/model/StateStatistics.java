package com.koley.kaustav.covid19.CovidTracker.model;

public class StateStatistics {
	
	private String state;
	private String country;
	private int totalCasesByToday;
	private int diffFromPrevDay;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalCasesByToday() {
		return totalCasesByToday;
	}
	public void setTotalCasesByToday(int currentDayCases) {
		this.totalCasesByToday = currentDayCases;
	}
	@Override
	public String toString() {
		return "StateStatistics [state=" + state + ", country=" + country + ", totalCasesByToday=" + totalCasesByToday
				+ "]";
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}

}

package com.knf.dev.CL_Simulator.entity;



public class Team {
	private String countryCode;
	private String teamName;
	private int eloPoints;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getEloPoints() {
		return eloPoints;
	}

	public void setEloPoints(int eloPoints) {
		this.eloPoints = eloPoints;
	}

}

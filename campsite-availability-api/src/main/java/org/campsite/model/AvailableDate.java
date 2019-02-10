package org.campsite.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableDate {
	
	@JsonProperty("availableDate")
	private List<Date> availableDates = null;
	
	public AvailableDate() {
		
	}
	public AvailableDate(List<Date> availableDates) {
		this.availableDates = availableDates;
	}
	public List<Date> getAvailableDates() {
		return availableDates;
	}
	public void setAvailableDates(List<Date> availableDates) {
		this.availableDates = availableDates;
	}	

	
}

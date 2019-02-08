 package org.campsite.controller;

import java.util.Date;
import java.util.List;

import org.campsite.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/availabilities")
public class AvailabilityController {
	
	@Autowired
	private ReservationService service;
	
    @GetMapping
    public ResponseEntity<Object> getAvailabilities(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
    	List<Date> list;
    	try {
    		list = service.getAvailableDates(startDate, endDate);
		} catch (Exception e) {
			//TODO log
			e.printStackTrace();
			return new ResponseEntity<Object>("unexpected error", HttpStatus.NOT_MODIFIED);
		}	
    	
    	return new ResponseEntity<Object>(list, HttpStatus.OK);
    }	   
    
}
 package org.campsite.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.campsite.error.ParameterMissingException;
import org.campsite.model.AvailableDate;
import org.campsite.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/api/v1/availabilities")
public class AvailabilityController {
	
	@Autowired
	private ReservationService service;
	
    @GetMapping(produces="application/json")
    @Valid
    public ResponseEntity<Object> getAvailabilities(@Valid @Nullable @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") @JsonFormat(timezone="GMT-3") Date startDate, 
    												@Valid @Nullable @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") @JsonFormat(timezone="GMT-3") Date endDate) {

    	// Conditions validation
		if(startDate==null && !(endDate==null)) { // Exception in case just start date was passed as parameter
			throw new ParameterMissingException();
		}else if(!(startDate==null )&& endDate==null) { // Exception in case just end date was passed as parameter
			throw new ParameterMissingException();
		}else if(startDate==null || endDate==null) { // In case of both parameters are null, the system will use the default dates
			startDate=getDefaultStartDate();
			endDate=getDefaultEndDate();
		}
		
		AvailableDate availableDate = new AvailableDate(service.getAvailableDates(startDate, endDate));

    	return new ResponseEntity<Object>(availableDate, HttpStatus.OK);
    }
    


	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}

    private Date getDefaultStartDate() {
    	Calendar cl = Calendar.getInstance();
    	cl.setTime(new Date());
    	cl.set(Calendar.HOUR, 0);
    	cl.set(Calendar.MINUTE, 0);
    	cl.set(Calendar.SECOND, 0);
    	System.out.println(cl.getTime());
    	return cl.getTime();
    }
    
    private Date getDefaultEndDate() {
    	Calendar cl = Calendar.getInstance();
    	cl.setTime(new Date());
    	cl.add(Calendar.MONTH, 1);
    	cl.set(Calendar.HOUR, 0);
    	cl.set(Calendar.MINUTE, 0);
    	cl.set(Calendar.SECOND, 0);
    	System.out.println(cl.getTime());
    	return cl.getTime();
    }   
    
}
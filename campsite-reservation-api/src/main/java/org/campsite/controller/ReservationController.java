 package org.campsite.controller;

import javax.validation.Valid;

import org.campsite.model.ReservationRequest;
import org.campsite.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	/**
	 * Get campsite reservation details
	 * @param bookingIdentifier String
	 * @return BookIdentifier Object
	 */
    @GetMapping(path="/{bookingIdentifier}")
    public ResponseEntity<Object> getReservation(@PathVariable String bookingIdentifier) {
    	return new ResponseEntity<Object>(service.get(bookingIdentifier), HttpStatus.OK);
    }	
	
	/**
	 * Request a campsite reservation
	 * @param ReservationRequest object
	 * @return bookingIdentifier
	 */
    @PostMapping
    public ResponseEntity<Object> addReservation(@Valid @RequestBody ReservationRequest rr) {

    	try {
    		service.save(rr); 
		} catch (Exception e) {
			//TODO log
			e.printStackTrace();
			return new ResponseEntity<Object>("unexpected error", HttpStatus.NOT_MODIFIED);
		}	
    	
    	return new ResponseEntity<Object>("Reservation Added", HttpStatus.CREATED);
    }
    
	/**
	 * Request a campsite reservation update
	 * @param reservationItem Object
	 * @param bookingIdentifier String
	 * @return HTTP status code
	 */
    @PutMapping(path="/{bookingIdentifier}")
    public ResponseEntity<Object> updateReservation(@RequestBody ReservationRequest reservationItem, @PathVariable String bookingIdentifier) {
    	try {
    		service.update(reservationItem,bookingIdentifier); 
		} catch (Exception e) {
			//TODO log
			e.printStackTrace();
			return new ResponseEntity<Object>("unexpected error", HttpStatus.NOT_MODIFIED);
		}	
    	
    	return new ResponseEntity<Object>("Reservation updated", HttpStatus.OK);
 
    }    
  
	/**
	 * Request a campsite cancellation
	 * @param bookingIdentifier String
	 * @return HTTP status code
	 */
    @DeleteMapping(path="/{bookingIdentifier}")
    public ResponseEntity<Object> cancelReservation(@PathVariable String bookingIdentifier) {
    	try {
    		service.delete(bookingIdentifier); 
		} catch (Exception e) {
			//TODO log
			e.printStackTrace();
			return new ResponseEntity<Object>("unexpected error", HttpStatus.NOT_MODIFIED);
		}	
    	
    	return new ResponseEntity<Object>("Reservation cancelled", HttpStatus.OK);    	
    	
    	
    }   
    
}
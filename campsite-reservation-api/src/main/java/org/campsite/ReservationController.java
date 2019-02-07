package org.campsite;

import org.campsite.dao.ReservationRequest;
import org.campsite.dao.ReservationService;
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
    	return new ResponseEntity<Object>("setup a book identifier", HttpStatus.OK);
    }	
	
	/**
	 * Request a campsite reservation
	 * @param ReservationRequest object
	 * @return bookingIdentifier
	 */
    @PostMapping
    public ResponseEntity<Object> addReservation(@RequestBody ReservationRequest rr) {

    	//repository.save(new Reservation("12546", rr.getFullName(), rr.getEmail(), rr.getStartDate(), rr.getEndDate())); 	
    	//ReservationService rs = new ReservationService();
    	service.save(rr);
    	//repository.deleteAll();
    	// Connect DB
    
    	// Convert Object to JSON
    	
    	// Check if dates were reserved already
    	
    	// Save the document
    	
    	// Close DB
    	
    	
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
    	return new ResponseEntity<Object>("Reservation updated", HttpStatus.OK);
    }    
  
	/**
	 * Request a campsite cancellation
	 * @param bookingIdentifier String
	 * @return HTTP status code
	 */
    @DeleteMapping(path="/{bookingIdentifier}")
    public ResponseEntity<Object> cancelReservation(@PathVariable String bookingIdentifier) {
    	return new ResponseEntity<Object>("", HttpStatus.OK);
    }     
    
}
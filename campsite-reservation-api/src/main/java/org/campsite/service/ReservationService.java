package org.campsite.service;
import java.util.Date;

import org.bson.types.ObjectId;
import org.campsite.model.Reservation;
import org.campsite.model.ReservationRepository;
import org.campsite.model.ReservationRequest;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
 
@Service
public class ReservationService {
 
    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationService() {
  	  SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
	}
    
    public void deleteAll() {
    	reservationRepository.deleteAll();
    }

    public void save(ReservationRequest item) {
    	
    	ObjectId objectId = ObjectId.get();    	
		Hashids hashids = new Hashids(objectId.toString(),8);
		String bookIdentification = hashids.encode(1L);
 		
    	Reservation res = new Reservation(objectId, 
    									  bookIdentification, 
    									  item.getFullName(), 
    									  item.getEmail(), 
    									  item.getStartDate(), 
    									  item.getEndDate(),
    									  new Date());
    	reservationRepository.save(res);
    }
    
    public void update(ReservationRequest item, String bookingIdentifier) {
    	
    	Reservation res = reservationRepository.findByBookingIdentifier(bookingIdentifier);
	    res.setFullName(item.getFullName()); 
	    res.setEmail(item.getEmail());
	    res.setStartDate(item.getStartDate()); 
	    res.setEndDate(item.getEndDate());
	    res.setRequestCreationDate(new Date());
    	reservationRepository.save(res);
    }    
    
    public void delete(String bookingIdentifier) {
    	reservationRepository.deleteByBookingIdentifier(bookingIdentifier);
    }
    
    public Reservation get(String bookingIdentifier) {
    	return reservationRepository.findByBookingIdentifier(bookingIdentifier);
    }    
 
}

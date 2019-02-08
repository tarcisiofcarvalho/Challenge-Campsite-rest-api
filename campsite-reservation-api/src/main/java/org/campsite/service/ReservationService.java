package org.campsite.service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    	
    	
    	List<Date> list = getReservedDates(item.getStartDate(),item.getEndDate());
    	
    	if(list.size()==0) {
	    	ObjectId objectId = ObjectId.get();    	
			Hashids hashids = new Hashids(objectId.toString(),8);
			String bookIdentification = hashids.encode(1L);
	 		
			Calendar endDateCal = Calendar.getInstance();
			endDateCal.setTime(item.getEndDate());
			endDateCal.add(Calendar.HOUR_OF_DAY, 23);
			endDateCal.add(Calendar.MINUTE, 59);
			endDateCal.add(Calendar.SECOND, 59);
			
	    	Reservation res = new Reservation(objectId, 
	    									  bookIdentification, 
	    									  item.getFullName(), 
	    									  item.getEmail(), 
	    									  item.getStartDate(), 
	    									  endDateCal.getTime(),
	    									  new Date());
	    	reservationRepository.save(res);
    	}else {
    		//TODO
    		for(Date dt: list)
    			System.out.println("Reserved date: " + dt);
    	}
    }
    
    public void update(ReservationRequest item, String bookingIdentifier) {
    	
    	Reservation res = reservationRepository.findByBookingIdentifier(bookingIdentifier);
    	
    	List<Date> list = getReservedDates(item.getStartDate(),item.getEndDate(),res.getId());
    	
    	if(list.size()==0) {
			Calendar endDateCal = Calendar.getInstance();
			endDateCal.setTime(item.getEndDate());
			endDateCal.add(Calendar.HOUR_OF_DAY, 23);
			endDateCal.add(Calendar.MINUTE, 59);
			endDateCal.add(Calendar.SECOND, 59);
			
		    res.setFullName(item.getFullName()); 
		    res.setEmail(item.getEmail());
		    res.setStartDate(item.getStartDate()); 
		    res.setEndDate(endDateCal.getTime());
		    res.setRequestCreationDate(new Date());
	    	reservationRepository.save(res);
    	}else {
    		//TODO
    		for(Date dt: list)
    			System.out.println("Reserved date: " + dt);
    	}
    }    
    
    public void delete(String bookingIdentifier) {
    	reservationRepository.deleteByBookingIdentifier(bookingIdentifier);
    }
    
    public Reservation get(String bookingIdentifier) {
    	return reservationRepository.findByBookingIdentifier(bookingIdentifier);
    }
    
    public List<Date> getReservedDates(Date startDate, Date endDate) {
		List<Date> list = new ArrayList<Date>();
		
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTime(startDate);
		
		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTime(endDate);
		
		while(endDateCal.getTime().compareTo(startDateCal.getTime())>=0) {
			if(reservationRepository.findByDate(startDateCal.getTime()).size()>0) {
				list.add(startDateCal.getTime());
			}
			startDateCal.add(Calendar.DATE, 1);
		}   	
    	return list;
    }
    
    public List<Date> getReservedDates(Date startDate, Date endDate, ObjectId id) {
		List<Date> list = new ArrayList<Date>();
		
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTime(startDate);
		
		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTime(endDate);
		
		while(endDateCal.getTime().compareTo(startDateCal.getTime())>=0) {
			if(reservationRepository.findByDateAndId(startDateCal.getTime(),id).size()>0) {
				list.add(startDateCal.getTime());
			}
			startDateCal.add(Calendar.DATE, 1);
		}   	
    	return list;
    }    
    
    
    
/*    private boolean comparePeriod(Date newStart, Date newEnd, Date currentStart, Date currentEnd) {
    	
    	Calendar currentEndCal = Calendar.getInstance();   	
    	currentEndCal.setTime(currentEnd); 	
    	currentEndCal.set(Calendar.HOUR, 0);
    	currentEndCal.set(Calendar.MINUTE, 0);
    	currentEndCal.set(Calendar.SECOND, 0);
    	
    	if(newStart.compareTo(currentStart)==0 || newEnd.compareTo(currentEndCal.getTime())==0)
    		return true;
    	return true;
    }*/
     
}

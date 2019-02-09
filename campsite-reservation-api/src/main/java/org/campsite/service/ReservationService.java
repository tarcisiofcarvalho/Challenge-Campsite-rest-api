package org.campsite.service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.types.ObjectId;
import org.campsite.error.MaxReservationDaysException;
import org.campsite.error.RequestReservationTimeException;
import org.campsite.error.ReservationDatesNotAvailableException;
import org.campsite.model.Reservation;
import org.campsite.model.ReservationRepository;
import org.campsite.model.ReservationRequest;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;
 
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
			endDateCal.set(Calendar.HOUR_OF_DAY, 23);
			endDateCal.set(Calendar.MINUTE, 59);
			endDateCal.set(Calendar.SECOND, 59);
			
	    	Reservation res = new Reservation(objectId, 
	    									  bookIdentification, 
	    									  item.getFullName(), 
	    									  item.getEmail(), 
	    									  item.getStartDate(), 
	    									  endDateCal.getTime(),
	    									  new Date());
	    	reservationRepository.save(res);
    	}else {
    		String jsonFormat = new Gson().toJson(list);
    		throw new ReservationDatesNotAvailableException(jsonFormat);
    	}
    }
    
    public void update(ReservationRequest item, String bookingIdentifier) {
    	
    	Reservation res = reservationRepository.findByBookingIdentifier(bookingIdentifier);
    	
    	List<Date> list = getReservedDates(item.getStartDate(),item.getEndDate(),res.getId());
    	
    	if(list.size()==0) {
			Calendar endDateCal = Calendar.getInstance();
			endDateCal.setTime(item.getEndDate());
			endDateCal.set(Calendar.HOUR_OF_DAY, 23);
			endDateCal.set(Calendar.MINUTE, 59);
			endDateCal.set(Calendar.SECOND, 59);
			
		    res.setFullName(item.getFullName()); 
		    res.setEmail(item.getEmail());
		    res.setStartDate(item.getStartDate()); 
		    res.setEndDate(endDateCal.getTime());
		    res.setRequestCreationDate(new Date());
	    	reservationRepository.save(res);
    	}else {
    		String jsonFormat = new Gson().toJson(list);
    		throw new ReservationDatesNotAvailableException(jsonFormat);
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
    
    public void checkRequestConditions(Date startDate, Date endDate) {
    	
    	// The campsite can be reserved for max 3 days.
    	long x = TimeUnit.DAYS.convert((endDate.getTime() - startDate.getTime()), TimeUnit.MILLISECONDS);
    	if(x>2 || x <=0)
    		throw new MaxReservationDaysException();

    	// The campsite can be reserved minimum 1 day(s) ahead of arrival and up to 1 month in advance
    	Calendar tempCal = Calendar.getInstance();
    	tempCal.setTime(new Date());
    	tempCal.add(Calendar.MONTH, 1);
    	if((startDate.compareTo(new Date())<=0) || (startDate.compareTo(tempCal.getTime())>0))
    		throw new RequestReservationTimeException();

    }
     
}

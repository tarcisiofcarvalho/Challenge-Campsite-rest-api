package org.campsite.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.campsite.model.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Service
public class ReservationService {
 
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    MongoTemplate mongoTemplate;

    public ReservationService() {
  	  SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
	}

    /**
     * Return the list of available campsite dates in the range passed
     * @param startDate
     * @param endDate
     * @return List<Date>
     */
    public List<Date> getAvailableDates(Date startDate, Date endDate) {
		
    	List<Date> list = new ArrayList<Date>();
		
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTime(startDate);
		
		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTime(endDate);

		// Execute reservation query for each range date 
		while(endDateCal.getTime().compareTo(startDateCal.getTime())>=0) {
			if(reservationRepository.findByDate(startDateCal.getTime()).size()==0)
				list.add(startDateCal.getTime());
			startDateCal.add(Calendar.DATE, 1);
		}   	
    	return list;
    } 
}

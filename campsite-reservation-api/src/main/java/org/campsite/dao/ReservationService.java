package org.campsite.dao;
import org.bson.types.ObjectId;
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

    public void save(ReservationRequest rr) {
    	Reservation res = new Reservation(ObjectId.get(), "12233", rr.getFullName(), rr.getEmail(), rr.getStartDate(), rr.getEndDate());
    	reservationRepository.save(res);
    }
 
}

package org.campsite.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public interface ReservationRepository extends MongoRepository<Reservation, String >{
	public Reservation findByBookingIdentifier(String bookingIdentifier);
	
	public void deleteByBookingIdentifier(String bookingIdentifier);
	
	@Query("{$and: [ { startDate: { $lte: ?0 } }, { endDate: { $gte: ?0 } } ] }")
    public List<Reservation> findByDate(@DateTimeFormat(iso = ISO.DATE) Date date) ;
	
	@Query("{$and: [ { startDate: { $lte: ?0 } }, { endDate: { $gte: ?0 } }, { id : { $ne: ?1} } ] }")
    public List<Reservation> findByDateAndId(@DateTimeFormat(iso = ISO.DATE) Date date, ObjectId id) ;
	
}

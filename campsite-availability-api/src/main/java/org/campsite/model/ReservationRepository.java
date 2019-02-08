package org.campsite.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public interface ReservationRepository extends MongoRepository<Reservation, String >{

	@Query("{$and: [ { startDate: { $lte: ?0 } }, { endDate: { $gte: ?0 } } ] }")
    public List<Reservation> findByDate(@DateTimeFormat(iso = ISO.DATE) Date date) ;
	
}

package org.campsite.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String >{
	public Reservation findByBookingIdentifier(String bookingIdentifier);
	public void deleteByBookingIdentifier(String bookingIdentifier);
}

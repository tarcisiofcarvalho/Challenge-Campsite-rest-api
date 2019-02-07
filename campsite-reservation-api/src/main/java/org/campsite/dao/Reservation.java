package org.campsite.dao;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="reservations")
public class Reservation{
	
  @Id
  private ObjectId id;
  
  private String bookingIdentifier;
  private String fullName;
  private String email;
  private String startDate;
  private String endDate;

  public Reservation() {

  }
 
	public Reservation(ObjectId id, String bookingIdentifier, String fullName, String email, String startDate,
			String endDate) {
		super();
		this.id = id;
		this.bookingIdentifier = bookingIdentifier;
		this.fullName = fullName;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getBookingIdentifier() {
		return bookingIdentifier;
	}

	public void setBookingIdentifier(String bookingIdentifier) {
		this.bookingIdentifier = bookingIdentifier;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}

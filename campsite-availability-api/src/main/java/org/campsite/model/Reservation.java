package org.campsite.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection="reservations")
public class Reservation{
	
	@Id
	private ObjectId id;
	
	private String bookingIdentifier;
	private String fullName;
	private String email;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date requestCreationDate;
	
	public Reservation() {
	}
	
	public Reservation(ObjectId id, String bookingIdentifier, String fullName, String email, Date startDate, Date endDate,
			Date requestCreationDate) {
		super();
		this.id = id;
		this.bookingIdentifier = bookingIdentifier;
		this.fullName = fullName;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requestCreationDate = requestCreationDate;
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
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getRequestCreationDate() {
		return requestCreationDate;
	}
	
	public void setRequestCreationDate(Date requestCreationDate) {
		this.requestCreationDate = requestCreationDate;
	}
 

}

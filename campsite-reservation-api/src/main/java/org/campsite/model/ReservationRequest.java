package org.campsite.model;

import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ReservationRequest   {
  
  private @Valid String fullName;
  private @Valid String email;
  private @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate;
  private @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate;

  @JsonProperty("fullName")
  @NotNull
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @JsonProperty("email")
  @NotNull
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  @JsonProperty("startDate")
  @NotNull
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  
  @JsonProperty("endDate")
  @NotNull
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}
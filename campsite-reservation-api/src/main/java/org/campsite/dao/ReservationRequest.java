package org.campsite.dao;

import javax.validation.constraints.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ReservationRequest   {
  
  private @Valid String fullName = null;
  private @Valid String email = null;
  private @Valid String startDate = null;
  private @Valid String endDate = null;

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
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  
  @JsonProperty("endDate")
  @NotNull
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

}